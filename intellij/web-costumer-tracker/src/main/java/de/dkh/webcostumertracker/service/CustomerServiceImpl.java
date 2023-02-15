package de.dkh.webcostumertracker.service;

import de.dkh.webcostumertracker.dao.CustomerDAO;
import de.dkh.webcostumertracker.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service Layer according to "Service Facade Design Pattern":
 * Sits between {@linkplain de.dkh.webcostumertracker.controller.CustomerController} and {@linkplain de.dkh.webcostumertracker.dao.CustomerDAO}.
 * Purpose: Data integration from multiple datasources (DAOs).
 * We annotate the service class with a special {@linkplain Service} annotation to enable Spring to find it during the component scan.
 * Actually is {@linkplain Service} an implementation of {@linkplain org.springframework.stereotype.Component} (like {@linkplain org.springframework.stereotype.Repository} and {@linkplain org.springframework.stereotype.Controller}).
 * <p>
 * <p>
 * DI of {@linkplain de.dkh.webcostumertracker.dao.CustomerDAO}: Spring will scan for all implementations of {@linkplain CustomerDAO}
 * and since we only have {@linkplain de.dkh.webcostumertracker.dao.CustomerDAOImpl} it will autowire this one.
 * If we had multiple implementations we could use {@linkplain Qualifier} (see deeper).
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    @Qualifier("customerDAOImpl")
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public Customer getCustomer(long id) {
        return customerDAO.getCustomer(id);
    }

    /**
     * Very important concept of handling {@linkplain org.hibernate.Transaction}.
     * Actually we do NOT need to begin and close transactions, Spring do all this behind the scene!
     * We just need to annotate the method with {@linkplain Transactional}.
     * Best practice here is to keep {@linkplain Transactional} with a service layer. All DAOs are then running in a "service context".
     *
     * @return
     */
    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }
}
