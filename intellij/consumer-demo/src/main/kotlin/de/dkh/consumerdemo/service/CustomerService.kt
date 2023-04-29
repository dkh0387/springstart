package de.dkh.consumerdemo.service

import de.dkh.consumerdemo.entity.Customer
import de.dkh.consumerdemo.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class CustomerService(@Autowired private val customerRepository: CustomerRepository) {

    fun getById(id: Int): Customer? = customerRepository.findById(id).get()

    @Throws(IllegalArgumentException::class)
    fun getByFirstName(firstName: String): Customer? {
        return customerRepository.findByFirstName(customerRepository.findAll().toList(), firstName)
    }


}