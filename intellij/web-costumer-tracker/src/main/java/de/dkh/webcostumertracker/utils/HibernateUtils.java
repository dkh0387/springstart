package de.dkh.webcostumertracker.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import de.dkh.webcostumertracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    /**
     * It is a heavyweight object created only once.
     * From this we can create different {@linkplain Session} objects for a unit of work.
     * For testing purpose we will inject {@linkplain HibernateUtils} via constructor and mock everything away.
     * <p>
     * NOTE: it is indeed important to add ALL annoted entity classes being used,
     * otherwise hibernate will not find them!
     */
    public SessionFactory getSessionFactoryInstance() {

        if (sessionFactory == null) {
            final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Customer.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public Session getSession() {
        final Session currentSession = getSessionFactoryInstance().getCurrentSession();

        if (currentSession.isOpen()) {
            return currentSession;
        } else {
            return getSessionFactoryInstance().openSession();
        }
    }

    public Transaction getTransaction() {
        final Transaction transaction = getSession().getTransaction();

        if (transaction.isActive()) {
            return transaction;
        } else {
            return getSession().beginTransaction();
        }
    }
}
