package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Review;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Example of create multiple {@linkplain Review} on a {@linkplain Course}.
 * We do NOT need to explicitly save the reviews, adding them to the course is enough, so long we keep the session open!
 */
public class CreateCourseReviewsDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Course course = (Course) persistentDAO.get(11, Course.class, hibernateUtils.getSession(), false);
            System.out.println("Course: " + course);

            Review review1 = new Review("Amazing course!", course);
            Review review2 = new Review("It was very good!", course);
            Review review3 = new Review("I found the course a bit difficult!", course);
            hibernateUtils.getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}