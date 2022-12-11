package de.dkh.core.data;

import org.springframework.data.repository.CrudRepository;

import de.dkh.core.Taco;

/**
 * Using Spring data JPA we do not need to explicitly define the methods to be
 * implemented, but just use the {@linkplain CrudRepository} insteed. Just
 * define the entity type and the id data type.
 * 
 * @author dkh
 *
 */
public interface TacoRepositoiry extends CrudRepository<Taco, Long> {

}
