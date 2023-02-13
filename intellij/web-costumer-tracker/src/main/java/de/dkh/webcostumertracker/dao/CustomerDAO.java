package de.dkh.webcostumertracker.dao;

import de.dkh.webcostumertracker.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public long saveCustomer();

    public Customer getCustomer();

    public List<Customer> getCustomers();

    public void deleteCustomer();
}
