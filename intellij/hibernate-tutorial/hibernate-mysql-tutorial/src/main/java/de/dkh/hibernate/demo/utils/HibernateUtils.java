package de.dkh.hibernate.demo.utils;

import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    /**
     * It is a heavyweight object created only once.
     * From this we can create different {@linkplain Session} objects for a unit of work.
     * For testing purpose we will inject {@linkplain HibernateUtils} via constructor and mock everything away.
     *
     * @return
     */
    public SessionFactory getSessionFactoryInstance() {

        if (sessionFactory == null) {
            final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Student.class);
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
