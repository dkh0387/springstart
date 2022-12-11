package de.dkh.core;

import java.sql.Types;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * POJO for an Ingredient of a taco. It calls "domain class" in SPRING context.
 * Getter/Setter are generated for all final fields using {@code @Data}.
 * 
 * @author dkh
 *
 */
//creates Setter/Getter for all final fields as well the all args constructor
@Data
// needed since @NoArgsConstructor removes the constructor created by @Data
@RequiredArgsConstructor
//private constructor requered by JPA; force = true inits all final fields with null
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
public class Ingredient {

	public final static String PROP_ID = "id";
	public final static String PROP_NAME = "name";
	public static final String PROP_TYPE = "type";
	public static final String TABLE_NAME = "Ingredient";
	public static final ObjectProperty NAME = new ObjectProperty(PROP_NAME, Types.VARCHAR);
	public static final ObjectProperty TYPE = new ObjectProperty(PROP_TYPE, Types.JAVA_OBJECT);

	@Id
	private final String id;
	private final String name;
	private final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
	}

}
