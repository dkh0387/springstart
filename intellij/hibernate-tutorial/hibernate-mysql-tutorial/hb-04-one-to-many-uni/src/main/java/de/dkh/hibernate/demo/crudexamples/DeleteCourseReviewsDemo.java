package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Because of adding {@linkplain javax.persistence.CascadeType#REMOVE} to {@linkplain Course#getReviews()}
 * hibernate deletes all {@linkplain de.dkh.hibernate.demo.entity.Review} objects by deleting the {@linkplain Course}.
 */
public class DeleteCourseReviewsDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Course course = (Course) persistentDAO.get(11, Course.class, hibernateUtils.getSession(), false);
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Course: " + course);
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Reviews: " + course.getReviews());
            System.out.println("-------------------------------------------------------------------------");

            persistentDAO.delete(course, hibernateUtils.getSession());
            System.out.println("Course deleted!");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }

}