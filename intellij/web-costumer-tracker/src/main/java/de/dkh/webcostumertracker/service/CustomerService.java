package de.dkh.webcostumertracker.service;

import de.dkh.webcostumertracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);
}
