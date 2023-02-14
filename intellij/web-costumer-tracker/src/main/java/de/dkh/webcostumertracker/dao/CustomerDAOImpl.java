package de.dkh.webcostumertracker.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import de.dkh.webcostumertracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * We annotate the DAO implementation as {@linkplain Repository}.
 * It is a special {@linkplain org.springframework.stereotype.Component} extension for DAO classes.
 * Spring will automatically find it via component scan.
 * Another big advantage: Spring will automatically translate all JDBC exceptions to unchecked exceptions.
 * <p>
 * <p>
 * NOTE: we bind the {@linkplain SessionFactory} object via {@linkplain Autowired}.
 * We do NOT explicity say, which type of factory is meaning, Spring will peak up the right one using
 * {@code dispather_servlet.xml}, where we explicitly set the bean id {@code sessionFactory}
 * to the {@linkplain org.springframework.orm.hibernate5.LocalSessionFactoryBean}.
 * The session factory itself knows via {@code dispather_servlet.xml},
 * which data source {@linkplain ComboPooledDataSource} to use.
 * Further the session factory is enabled with component entity scan and is binded to {@linkplain org.springframework.orm.hibernate5.HibernateTransactionManager}.
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long saveCustomer() {
        return 0;
    }

    @Override
    public Customer getCustomer() {
        return null;
    }


    @Override
    public List<Customer> getCustomers() {

        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> customerQuery = currentSession.createQuery("from Customer", Customer.class);

        return customerQuery.getResultList();
    }

    @Override
    public void deleteCustomer() {

    }
}
