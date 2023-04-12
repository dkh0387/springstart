package de.dkh.customerapi.controller;

import de.dkh.customerapi.entity.Customer;
import de.dkh.customerapi.repository.CustomerRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Example of usage a REST Spring controller.
 * In difference to a classical Controller (for routing/rendering views),
 * here we deliver data (in our case the customer list) to another service per REST-API.
 * By calling {@code "http://localhost:8080/customerList"} we will get the customer list as JSON object.
 */
@RestController
@RequestMapping("/customerList")
public class CustomerController {
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("")
    public List<Customer> getCustomerList() {
        return customerRepository.findAll();
    }


}
