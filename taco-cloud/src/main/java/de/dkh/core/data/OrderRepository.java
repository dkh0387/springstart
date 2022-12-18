package de.dkh.core.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import de.dkh.core.Order;

/**
 * Using Spring data JPA we do not need to explicitly define the methods to be
 * implemented, but just use the {@linkplain CrudRepository} insteed. Just
 * define the entity type and the id data type. If i want a special method does
 * not defined by the super class i can use some kind of "domain language"
 * {@code DSL} to let the {@linkplain CrudRepository} recognize the goal of the
 * method (linke {@code lombok} Getter.
 * 
 * @author dkh
 *
 */
public interface OrderRepository extends CrudRepository<Order, Long> {

	/**
	 * There is a field {@linkplain Order#getZip()}, so i the super class recognize
	 * by type {@code findBy} in method body, the parameter and the return type what
	 * the method should do.
	 * 
	 * @param zip
	 * @return
	 */
	List<Order> findByZip(String zip);

	/**
	 * This one is recognized as well!
	 * 
	 * @param zip
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */
	List<Order> readByZipAndPlacedAtBetween(String zip, Date dateFrom, Date dateTo);

	/**
	 * {@code findBy + Order.name + And + Order.city + AllIgnoreCase}
	 * 
	 * @param name
	 * @param city
	 * @return
	 */
	List<Order> findByNameAndCityAllIgnoreCase(String name, String city);

	/**
	 * If i can not relay on auto recognition i can explicitly define the query
	 * should being executed.
	 * 
	 * @return
	 */
	@Query("from Order where city = 'Seattle'")
	List<Order> readOrdersDeliveredInSeattle();

}
