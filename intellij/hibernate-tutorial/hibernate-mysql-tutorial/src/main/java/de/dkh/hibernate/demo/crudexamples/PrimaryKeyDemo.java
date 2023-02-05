package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

public class PrimaryKeyDemo {

    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        StudentDAO studentDAO = new StudentDAO();

        try {
            Student student1 = new Student("Mark", "Müller", "mark@gmail.com");
            Student student2 = new Student("Elena", "Khaskina", "elena@gmail.com");
            Student student3 = new Student("Mary", "Jane", "mary@gmail.com");

            studentDAO.save(student1, hibernateUtils.getSession());
            studentDAO.save(student2, hibernateUtils.getSession());
            studentDAO.save(student3, hibernateUtils.getSession());

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        }
    }
}
