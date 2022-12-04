package de.dkh.core;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * We need to persist {@linkplain Order}. Data are writen into the requered
 * tables. Note: since we only execute INSERT commands here we are not going to
 * use {@linkplain PreparedStatementCreator} like in
 * {@linkplain JdbcTacoRepository} but the {@linkplain SimpleJdbcInsert}.
 * 
 * @author dkh
 *
 */
@Repository
public class JdbcOrderRepository extends JdbcRepository implements OrderRepository {

	private SimpleJdbcInsert orderInserter;
	private SimpleJdbcInsert orderTacosInserter;
	private ObjectMapper objectMapper;

	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		super(jdbc);
		orderInserter = new SimpleJdbcInsert(getJdbc()).withTableName(Order.TABLE_NAME)
				.usingGeneratedKeyColumns(Order.PROP_ID);
		orderTacosInserter = new SimpleJdbcInsert(getJdbc()).withTableName(OrderTacos.TABLE_NAME);
		objectMapper = new ObjectMapper();
	}

	@Override
	public Order save(Order order) {
		order.setPlacedAt(new Date());
		long id = saveOrderDetails(order);
		order.setId(id);
		order.getTacos().forEach(taco -> saveTacoToOrder(taco.getId(), order.getId()));
		return order;
	}

	/**
	 * Using {@linkplain ObjectMapper} we can create a property map for persisting.
	 * 
	 * @param order
	 * @return
	 */
	private long saveOrderDetails(Order order) {
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);
		values.put(Order.PROP_CREATED, order.getPlacedAt());
		return orderInserter.executeAndReturnKey(values).longValue();
	}

	private void saveTacoToOrder(long tacoId, long orderId) {
		Map<String, Object> values = new HashMap<>();
		values.put(OrderTacos.PROP_TACO.toLowerCase(), tacoId);
		values.put(OrderTacos.PROP_ORDER.toLowerCase(), orderId);
		orderTacosInserter.execute(values);
	}

}
