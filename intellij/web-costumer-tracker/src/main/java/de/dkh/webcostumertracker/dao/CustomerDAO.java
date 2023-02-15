package de.dkh.webcostumertracker.dao;

import de.dkh.webcostumertracker.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    public long saveCustomer(Customer customer);

    public Customer getCustomer(long id);

    public List<Customer> getCustomers();

    public void deleteCustomer();

    public void updateCustomer(Customer customer);
}
