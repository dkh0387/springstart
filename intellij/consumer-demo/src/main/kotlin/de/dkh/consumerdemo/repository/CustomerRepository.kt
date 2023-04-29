package de.dkh.consumerdemo.repository

import de.dkh.consumerdemo.entity.Customer
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Repository implementation: contains standard CRUD and custom methods.
 */
@Repository
interface CustomerRepository : CrudRepository<Customer, Int>, CustomerRepositoryCustom {
}