package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static de.dkh.hibernate.demo.HibernateUtils.getSessionFactoryInstance;

public class CreateStudentDemo {
    public static void main(String[] args) {
        /* Creating a new session factory. This is a heavyweight object for creating hibernate sessions.
           We do not need to specify the configuration resource here, since it always looks for `hibernate.cfg.xml` in `src/main/resources` folder!
         */
        Session currentSession = HibernateUtils.getSessionFactoryInstance().getCurrentSession();

        try (currentSession) {
            Student student = new Student("Denis", "Khaskin", "deniskh87@gmail.com");
            currentSession.beginTransaction();
            currentSession.save(student);
            currentSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            currentSession.getTransaction().rollback();
        }
    }


}