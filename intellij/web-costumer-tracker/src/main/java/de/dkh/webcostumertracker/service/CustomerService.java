package de.dkh.webcostumertracker.service;

import de.dkh.webcostumertracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    public Customer getCustomer(long id);

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public void updateCustomer(Customer customer);
}
