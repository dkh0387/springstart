package de.dkh.producerdemo.repository

import de.dkh.producerdemo.entity.CustomerEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * Repository implementation: contains standard CRUD and custom methods.
 */
@Repository
interface CustomerRepository : CrudRepository<CustomerEntity, Int>, CustomerRepositoryCustom {
}