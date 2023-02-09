package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

import java.util.List;

/**
 * Example of create multiple {@linkplain Student} on a {@linkplain Course}.
 */
public class CreateCourseStudentsDemo {

    public static String[] course_ids;
    public static String[] student_ids;

    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Course course = (Course) persistentDAO.get(Long.parseLong(args[0]), Course.class, hibernateUtils.getSession(), false);
            System.out.println("Course: " + course);

            Student student1 = new Student("Denis", "Khaskin", "denis@gmail.com");
            course.addStudent(student1);
            student1.addCourse(course);
            Student student2 = new Student("Elena", "Khaskina", "elena@gmail.com");
            course.addStudent(student2);
            student2.addCourse(course);

            persistentDAO.saveAll(List.of(student1, student2), hibernateUtils, false);

            student_ids = new String[]{String.valueOf(student1.getId()), String.valueOf(student2.getId())};

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
//            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}