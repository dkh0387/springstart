package de.dkh.core;

import java.sql.Types;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * Attributes here correcpods directly with the same named in
 * {@code design.html}.
 * 
 * @author dkh
 *
 */
@Data
@Entity
public class Taco {

	public final static String PROP_ID = "id";
	public final static String PROP_NAME = "name";
	public static final String PROP_CREATED = "createdAt";
	public static final String TABLE_NAME = "Taco";
	public static final ObjectProperty CREATED = new ObjectProperty(PROP_CREATED, Types.TIMESTAMP);
	public static final ObjectProperty NAME = new ObjectProperty(PROP_NAME, Types.VARCHAR);

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Date createdAt;
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;
	@ManyToMany(targetEntity = Ingredient.class)
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;
//	@Size(min = 1, message = "You must choose at least 1 ingredient")
//	private List<String> ingredients;

	@PrePersist
	void createdAt() {
		createdAt = new Date();
	}

}
