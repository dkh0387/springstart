package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * NOTE: by default is {@linkplain javax.persistence.FetchType#EAGER} in case of {@linkplain javax.persistence.OneToMany} relation,
 * so we have reviews on course loaded from beginning. If we change to {@linkplain javax.persistence.FetchType#LAZY}
 * we need to keep the session open by calling {@linkplain PersistentDAO#get(long, Class, Session, boolean)} with {@code false}.
 */
public class ReadCourseReviewsDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Course course = (Course) persistentDAO.get(10, Course.class, hibernateUtils.getSession(), false);
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Course: " + course);
            System.out.println("-------------------------------------------------------------------------");
            System.out.println("Reviews: " + course.getReviews());
            System.out.println("-------------------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }

}