package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Example of {@code update()}.
 * NOTE: there is no need to call {@code save()} etc. on object, {@code commit()} is enough.
 */
public class UpdateStudentDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        StudentDAO studentDAO = new StudentDAO();

        try {
            studentDAO.updateProperty(2, Student.class, hibernateUtils.getSession(), "email", "deniskh87@gmail.com");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        }
    }

}