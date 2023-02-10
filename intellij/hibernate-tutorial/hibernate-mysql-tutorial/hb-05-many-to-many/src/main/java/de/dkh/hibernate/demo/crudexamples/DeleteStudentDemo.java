package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Example of deleting a {@linkplain Student}.
 * NOTE: because of {@linkplain javax.persistence.CascadeType#REMOVE} is not added, we DO NOT delete associated {@linkplain Course} objects, only the rows in the linked table.
 */
public class DeleteStudentDemo {

    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Student student = (Student) persistentDAO.get(Long.parseLong(args[0]), Student.class, hibernateUtils.getSession(), false);
            System.out.println("\n\nStudent: " + student + "\n");

            persistentDAO.delete(student, hibernateUtils.getSession());
            System.out.println("\n\nStudent " + student + " deleted!" + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
//            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}