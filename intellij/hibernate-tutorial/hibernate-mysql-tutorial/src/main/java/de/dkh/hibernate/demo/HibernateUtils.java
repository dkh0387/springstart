package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactoryInstance() {

        if (sessionFactory == null) {
            final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(Student.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getSession() {
        final Session currentSession = HibernateUtils.getSessionFactoryInstance().getCurrentSession();

        if (currentSession.isOpen()) {
            return currentSession;
        } else {
            return HibernateUtils.getSessionFactoryInstance().openSession();
        }
    }

    public static Transaction getTransaction() {
        final Transaction transaction = getSession().getTransaction();

        if (transaction.isActive()) {
            return transaction;
        } else {
            return getSession().beginTransaction();
        }
    }
}
