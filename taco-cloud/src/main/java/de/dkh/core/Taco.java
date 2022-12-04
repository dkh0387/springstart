package de.dkh.core;

import java.sql.Types;
import java.util.Date;
//tag::all[]
//tag::allButValidation[]
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//end::allButValidation[]

//tag::allButValidation[]
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Attributes here correcpods directly with the same named in
 * {@code design.html}.
 * 
 * @author dkh
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Taco {
	
	public final static String PROP_ID = "id";
	public final static String PROP_NAME = "name";
	public static final String PROP_CREATED = "createdAt";
	public static final String TABLE_NAME = "Taco";
	public static final ObjectProperty CREATED = new ObjectProperty(PROP_CREATED, Types.TIMESTAMP);
	public static final ObjectProperty NAME = new ObjectProperty(PROP_NAME, Types.VARCHAR);

	private long id;
	private Date createdAt;

// end::allButValidation[]
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
// tag::allButValidation[]
	private String name;
// end::allButValidation[]
	@Size(min = 1, message = "You must choose at least 1 ingredient")
// tag::allButValidation[]
	private List<String> ingredients;

}
//end::allButValidation[]
//tag::end[]
