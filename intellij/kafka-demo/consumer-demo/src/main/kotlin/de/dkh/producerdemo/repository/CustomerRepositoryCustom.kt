package de.dkh.producerdemo.repository

import de.dkh.producerdemo.entity.Customer

/**
 * Custom DAO methods.
 */
interface CustomerRepositoryCustom {

    fun findByFirstName(customerList: List<Customer>, firstName: String): Customer?
}