package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.utils.HibernateUtils;

import java.util.List;
import java.util.Map;

/**
 * Example of using HQL (hibernate query language) to get the objects from database.
 * Note the string format especially by setting parameters ({@code ...=: instructorId}).
 * Since we are getting the courses via HQL and NOT via Getter we do not have any troubles with closed session.
 */
public class FetchJoinDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            Instructor instructor = (Instructor) instructorDAO.selectByIdQuery(1, Instructor.class, hibernateUtils.getSession());
            Long theInstructorId = instructor.getId();
            System.out.println("Instructor: " + instructor);
            List<Course> courses = (List<Course>) instructorDAO
                    .query(Course.class, String.format("SELECT c FROM %s i INNER JOIN %s c ON i.id =: instructorId"
                                    , Instructor.class.getSimpleName()
                                    , Course.class.getSimpleName())
                            , Map.of("instructorId", theInstructorId)
                            , hibernateUtils.getSession());
            System.out.println("Courses: " + courses);

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}