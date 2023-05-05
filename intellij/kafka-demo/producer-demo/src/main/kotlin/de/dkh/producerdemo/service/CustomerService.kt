package de.dkh.producerdemo.service

import de.dkh.producerdemo.entity.CustomerEntity
import de.dkh.producerdemo.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CustomerService(@Autowired private val customerRepository: CustomerRepository) {

    fun getById(id: Int): CustomerEntity? = customerRepository.findById(id).get()

    @Throws(IllegalArgumentException::class)
    fun getByFirstName(firstName: String): CustomerEntity? {
        return customerRepository.findByFirstName(customerRepository.findAll().toList(), firstName)
    }


}