package de.dkh.producerdemo.repository

import de.dkh.producerdemo.entity.Customer

class CustomerRepositoryImpl : CustomerRepositoryCustom {

    override fun findByFirstName(customerList: List<Customer>, firstName: String): Customer =
        customerList.first { it.firstName == firstName }
}