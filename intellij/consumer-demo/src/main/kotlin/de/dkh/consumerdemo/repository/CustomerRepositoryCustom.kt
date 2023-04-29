package de.dkh.consumerdemo.repository

import de.dkh.consumerdemo.entity.Customer

/**
 * Custom DAO methods.
 */
interface CustomerRepositoryCustom {

    fun findByFirstName(customerList: List<Customer>, firstName: String): Customer?
}