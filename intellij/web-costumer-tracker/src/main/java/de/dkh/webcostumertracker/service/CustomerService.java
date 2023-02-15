package de.dkh.webcostumertracker.service;

import de.dkh.webcostumertracker.entity.Customer;

import java.util.List;

public interface CustomerService {

    public Customer getCustomer(long id);

    public List<Customer> getCustomers();

    public List<Customer> getSortedCustomers(int colNumberToSort);

    public void saveCustomer(Customer customer);

    public void deleteCustomer(Customer customer);

    public List<Customer> searchCustomerByName(String searchName);


}
