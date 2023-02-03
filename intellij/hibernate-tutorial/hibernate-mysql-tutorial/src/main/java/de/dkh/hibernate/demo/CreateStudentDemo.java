package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;

public class CreateStudentDemo {
    public static void main(String[] args) {
        /* Creating a new session factory. This is a heavyweight object for creating hibernate sessions.
           We do not need to specify the configuration resource here, since it always looks for `hibernate.cfg.xml` in `src/main/resources` folder!
         */
        HibernateUtils hibernateUtils = new HibernateUtils();
        Session session = hibernateUtils.getSession();
        StudentDAO studentDAO = new StudentDAO();

        try {
            Student student = new Student("Denis", "Khaskin", "deniskh87@gmail.com");
            studentDAO.save(student, session);

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }


}