package de.dkh.consumerdemo.repository

import de.dkh.consumerdemo.entity.CustomerEntity

class CustomerRepositoryImpl : CustomerRepositoryCustom {

    override fun findByFirstName(customerEntityList: List<CustomerEntity>, firstName: String): CustomerEntity =
        customerEntityList.first { it.firstName == firstName }
}