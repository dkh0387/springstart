package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Example of deleting a {@linkplain Course}.
 * NOTE: because of {@linkplain javax.persistence.CascadeType#REMOVE} is not added, we DO NOT delete associated {@linkplain Student} objects, only the rows in the linked table.
 */
public class DeleteCourseDemo {

    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Course course = (Course) persistentDAO.get(Long.parseLong(args[0]), Course.class, hibernateUtils.getSession(), false);
            System.out.println("\n\nCourse: " + course + "\n");

            persistentDAO.delete(course, hibernateUtils.getSession());
            System.out.println("\n\nCourse " + course + " deleted!" + "\n");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
//            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}