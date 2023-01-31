package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class CreateStudentDemo {
    public static void main(String[] args) {
        /* Creating a new session factory. This is a heavyweight object for creating hibernate sessions.
           We do not need to specify the configuration resource here, since it always looks for `hibernate.cfg.xml` in `src/main/resources` folder!
         */
        final SessionFactory sessionFactory = createSessionFactory();
        Session currentSession = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            Student student = new Student("Denis", "Khaskin", "deniskh87@gmail.com");
            currentSession.beginTransaction();
            currentSession.save(student);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            currentSession.getTransaction().rollback();
        }
    }

    private static SessionFactory createSessionFactory() {
        final Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Student.class);
        return configuration.buildSessionFactory();
    }
}