package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Example of reading multiple {@linkplain Course} on a {@linkplain Student}.
 */
public class ReadCoursesForStudent {

    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Student student = (Student) persistentDAO.get(Long.parseLong(args[0]), Student.class, hibernateUtils.getSession(), false);
            System.out.println("\n\nStudent: " + student + "\n");
            System.out.println("\n\nCourses for the student " + student + " :" + student.getCourses() + "\n");

            hibernateUtils.getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
//            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}