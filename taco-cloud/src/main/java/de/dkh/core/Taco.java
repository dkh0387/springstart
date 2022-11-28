package de.dkh.core;

import java.util.Date;
//tag::all[]
//tag::allButValidation[]
import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//end::allButValidation[]

//tag::allButValidation[]
import lombok.Data;

/**
 * Attributes here correcpods directly with the same named in
 * {@code design.html}.
 * 
 * @author dkh
 *
 */
@Data
public class Taco {
	
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
