package de.dkh.consumerdemo.repository

import de.dkh.consumerdemo.entity.CustomerEntity

/**
 * Custom DAO methods.
 */
interface CustomerRepositoryCustom {

    fun findByFirstName(customerEntityList: List<CustomerEntity>, firstName: String): CustomerEntity?
}