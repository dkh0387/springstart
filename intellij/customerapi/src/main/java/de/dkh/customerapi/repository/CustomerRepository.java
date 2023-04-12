package de.dkh.customerapi.repository;

import de.dkh.customerapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring data JPA usage example.
 * As generics in interface: {@code <class, id_data_type>}.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
