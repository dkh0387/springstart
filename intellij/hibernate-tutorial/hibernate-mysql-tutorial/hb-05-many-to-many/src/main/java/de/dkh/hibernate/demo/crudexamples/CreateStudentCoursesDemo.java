package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Example of create multiple {@linkplain Course} on a {@linkplain Student}.
 * Actually it is an inverse process of {@linkplain CreateCourseStudentsDemo#main(String[])}.
 */
public class CreateStudentCoursesDemo {

    public static String[] course_ids;
    public static String[] student_ids;

    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Student student = (Student) persistentDAO.get(Long.parseLong(args[0]), Student.class, hibernateUtils.getSession(), false);
            System.out.println("Student: " + student);

            Course course1 = new Course("C++", student);
            Course course2 = new Course("Pascal", student);

            persistentDAO.save(course1, hibernateUtils.getSession(), false);
            persistentDAO.save(course2, hibernateUtils.getSession(), false);
            hibernateUtils.getSession().getTransaction().commit();
            System.out.println("Courses for the student " + student + " :" + student.getCourses());

            course_ids = new String[]{String.valueOf(course1.getId()), String.valueOf(course2.getId())};

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
//            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}