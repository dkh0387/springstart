package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * NOTE: since we excluded {@linkplain javax.persistence.CascadeType#REMOVE} explicitly,
 * we can delete a {@linkplain de.dkh.hibernate.demo.entity.Course} without deleting an {@linkplain Instructor}.
 */
public class DeleteCourseDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            Course course = (Course) instructorDAO.get(15, Course.class, hibernateUtils.getSession());
            System.out.println("Course: " + course);
            instructorDAO.delete(course, hibernateUtils.getSession());

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}