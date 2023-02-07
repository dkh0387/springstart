package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.utils.HibernateUtils;

import java.util.List;

public class CreateCoursesDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            Instructor instructor = (Instructor) instructorDAO.get(1, Instructor.class, hibernateUtils.getSession());
            /*
             * NOTE: we do have the cascade type `PERSIST` here,
             *  so we just need to bind instructor to courses and save the list.
             */
            Course course1 = new Course("JAVA", instructor);
            Course course2 = new Course("Python", instructor);

            instructorDAO.saveAll(List.of(course1, course2), hibernateUtils);

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}