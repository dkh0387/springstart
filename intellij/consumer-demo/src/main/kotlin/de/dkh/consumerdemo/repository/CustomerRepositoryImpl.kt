package de.dkh.consumerdemo.repository

import de.dkh.consumerdemo.entity.Customer

class CustomerRepositoryImpl : CustomerRepositoryCustom {

    override fun findByFirstName(customerList: List<Customer>, firstName: String): Customer =
        customerList.first { it.firstName == firstName }
}