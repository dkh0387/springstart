package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static de.dkh.hibernate.demo.HibernateUtils.getSessionFactoryInstance;

public class PrimaryKeyDemo {

    public static void main(String[] args) {
        final SessionFactory sessionFactory = getSessionFactoryInstance();
        Session currentSession = sessionFactory.getCurrentSession();

        try (sessionFactory) {
            Student student1 = new Student("Mark", "Müller", "mark@gmail.com");
            Student student2 = new Student("Elena", "Khaskina", "elena@gmail.com");
            Student student3 = new Student("Mary", "Jane", "mary@gmail.com");
            currentSession.beginTransaction();
            currentSession.save(student1);
            currentSession.save(student2);
            currentSession.save(student3);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            currentSession.getTransaction().rollback();
        }
    }
}
