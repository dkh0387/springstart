package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * NOTE: we read {@linkplain Instructor#getCourses()} here after getting an {@linkplain Instructor} from db.
 * This requires {@linkplain javax.persistence.FetchType#EAGER},
 * so the courses are loaded with their instructor from beginning.
 */
public class ReadInstructorCoursesDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            Instructor instructor = (Instructor) instructorDAO.get(1, Instructor.class, hibernateUtils.getSession());
            System.out.println("Instructor: " + instructor);
            System.out.println("Courses: " + instructor.getCourses());

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}