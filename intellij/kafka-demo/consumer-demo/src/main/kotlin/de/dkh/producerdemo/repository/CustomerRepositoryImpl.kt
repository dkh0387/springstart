package de.dkh.producerdemo.repository

import de.dkh.producerdemo.entity.CustomerEntity

class CustomerRepositoryImpl : CustomerRepositoryCustom {

    override fun findByFirstName(customerEntityList: List<CustomerEntity>, firstName: String): CustomerEntity =
        customerEntityList.first { it.firstName == firstName }
}