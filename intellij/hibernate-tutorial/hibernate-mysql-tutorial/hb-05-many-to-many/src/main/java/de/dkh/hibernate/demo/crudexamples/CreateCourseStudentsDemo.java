package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Course;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;

/**
 * Example of create multiple {@linkplain Student} on a {@linkplain Course}.
 * NOTE: we HAVE TO persist all {@linkplain Student} objects associated to a given {@linkplain Course} BEFORE {@linkplain Session#getTransaction()#commit()},
 * otherwise we get {@linkplain org.hibernate.HibernateException}
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

            Student student1 = new Student("Denis", "Khaskin", "denis@gmail.com", course);
            Student student2 = new Student("Elena", "Khaskina", "elena@gmail.com", course);
            persistentDAO.save(student1, hibernateUtils.getSession(), false);
            persistentDAO.save(student2, hibernateUtils.getSession(), false);
            hibernateUtils.getSession().getTransaction().commit();
            System.out.println("Students for the course " + course + " :" + course.getStudents());

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