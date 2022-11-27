package de.dkh.core;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * POJO for an Ingredient of a taco. It calls "domain class" in SPRING context.
 * Getter/Setter are ommited here...
 * 
 * @author dkh
 *
 */
@Data
@RequiredArgsConstructor
public class Ingredient {
	private final String id;
	private final String name;
	private final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE;
	}

}
