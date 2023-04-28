package de.dkh.producerdemo.repository

import de.dkh.producerdemo.entity.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Repository implementation: contains standard CRUD and custom methods.
 */
@Repository
interface CustomerRepository : CrudRepository<Customer, Int>, CustomerRepositoryCustom {
}