package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class ReadStudentDemo {

    public static void main(String[] args) {

        try {
            StudentDAO studentDAO = new StudentDAO();
            // query all students:
            List<Student> studentList = studentDAO.query(HibernateUtils.getSession());
            System.out.println(studentList.stream().map(Student::toString).collect(Collectors.toSet()));
            System.out.println("----------------------------------------------------------------------");

            // save a new student:
            Student student = new Student("Albert", "Einstein", "albert@gmail.com");
            studentDAO.save(student, HibernateUtils.getSession());
            System.out.println("----------------------------------------------------------------------");

            // get created student:
            Student createdStudent = studentDAO.get(student.getId(), HibernateUtils.getSession());
            System.out.println(createdStudent.toString());
            System.out.println("----------------------------------------------------------------------");

            HibernateUtils.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            HibernateUtils.getSession().getTransaction().rollback();
        }
    }

}