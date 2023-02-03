package de.dkh.hibernate.demo.crudbasics;

import de.dkh.hibernate.demo.HibernateUtils;
import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        Session session = hibernateUtils.getSession();
        StudentDAO studentDAO = new StudentDAO();

        try {
            Student student1 = new Student("Mark", "Müller", "mark@gmail.com");
            Student student2 = new Student("Elena", "Khaskina", "elena@gmail.com");
            Student student3 = new Student("Mary", "Jane", "mary@gmail.com");

            studentDAO.save(student1, session);
            studentDAO.save(student2, session);
            studentDAO.save(student3, session);

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
