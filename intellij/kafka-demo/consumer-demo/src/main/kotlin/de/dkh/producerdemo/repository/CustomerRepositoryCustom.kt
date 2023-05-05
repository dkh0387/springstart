package de.dkh.producerdemo.repository

import de.dkh.producerdemo.entity.CustomerEntity

/**
 * Custom DAO methods.
 */
interface CustomerRepositoryCustom {

    fun findByFirstName(customerEntityList: List<CustomerEntity>, firstName: String): CustomerEntity?
}