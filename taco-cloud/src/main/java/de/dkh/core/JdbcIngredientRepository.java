package de.dkh.core;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcIngredientRepository extends JdbcRepository implements IngredientRepository {

	private static final String WHERE_ID = " WHERE %s = ?";
	private static final String SELECT_FROM_INGREDIENT = "SELECT %s, %s, %s FROM %s";

	@Autowired
	public JdbcIngredientRepository(JdbcTemplate jdbc) {
		super(jdbc);
	}
//end::classShell[]

	// tag::finders[]
	@Override
	public Iterable<Ingredient> findAll() {
		return getJdbc().query(String.format(SELECT_FROM_INGREDIENT, Ingredient.PROP_ID, Ingredient.PROP_NAME,
				Ingredient.PROP_TYPE, Ingredient.TABLE_NAME), this::mapRowToIngredient);
	}

	// tag::findOne[]
	@Override
	public Ingredient findById(String id) {
		return getJdbc()
				.queryForObject(
						String.format(SELECT_FROM_INGREDIENT + WHERE_ID, Ingredient.PROP_ID, Ingredient.PROP_NAME,
								Ingredient.PROP_TYPE, Ingredient.TABLE_NAME, Ingredient.PROP_ID),
						this::mapRowToIngredient, id);
	}

	// end::findOne[]

	// end::finders[]

	/*
	 * //tag::preJava8RowMapper[]
	 * 
	 * @Override public Ingredient findOne(String id) { return jdbc.queryForObject(
	 * "select id, name, type from Ingredient where id=?", new
	 * RowMapper<Ingredient>() { public Ingredient mapRow(ResultSet rs, int rowNum)
	 * throws SQLException { return new Ingredient( rs.getString("id"),
	 * rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type"))); }; },
	 * id); } //end::preJava8RowMapper[]
	 */

	// tag::save[]
	@Override
	public Ingredient save(Ingredient ingredient) {
		getJdbc().update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(),
				ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}
	// end::save[]

	// tag::findOne[]
	// tag::finders[]
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}
	// end::finders[]
	// end::findOne[]

	/*
	 * //tag::classShell[] ... //end::classShell[]
	 */
//tag::classShell[]

}
//end::classShell[]
