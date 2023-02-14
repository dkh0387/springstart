package de.dkh.webcostumertracker.controller;

import de.dkh.webcostumertracker.entity.Customer;
import de.dkh.webcostumertracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * DI of {@linkplain CustomerService}: Spring will scan for all implementations of {@linkplain CustomerService}
 * and since we only have {@linkplain de.dkh.webcostumertracker.service.CustomerServiceImpl} it will autowire this one.
 * If we had multiple implementations we could use {@linkplain Qualifier} (see deeper).
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    @Qualifier("customerServiceImpl")
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        // get the customers using CustomerService
        List<Customer> customers = customerService.getCustomers();

        // add the customers to the model
        model.addAttribute("customers", customers);
        return "list-customers";
    }
}
