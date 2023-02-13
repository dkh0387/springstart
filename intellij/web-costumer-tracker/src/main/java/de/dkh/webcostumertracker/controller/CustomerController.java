package de.dkh.webcostumertracker.controller;

import de.dkh.webcostumertracker.dao.CustomerDAO;
import de.dkh.webcostumertracker.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * DI of {@linkplain CustomerDAO}: Spring will scan for all implementations of {@linkplain CustomerDAO}
 * and since we only have {@linkplain de.dkh.webcostumertracker.dao.CustomerDAOImpl} it will autowire this one.
 * If we had multiple implementations we could use {@linkplain Qualifier} (see deeper).
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    @Qualifier("customerDAOImpl")
    private CustomerDAO customerDAO;

    @RequestMapping("/list")
    public String listCustomers(Model model) {
        // get the customers using CustomerDAO
        List<Customer> customers = customerDAO.getCustomers();

        // add the customers to the model
        model.addAttribute("customers", customers);
        return "list-customers";
    }
}
