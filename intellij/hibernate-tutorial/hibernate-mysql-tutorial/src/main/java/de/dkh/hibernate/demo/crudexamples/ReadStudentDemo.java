package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ReadStudentDemo {

    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        StudentDAO studentDAO = new StudentDAO();

        try {
            // query all students:
            System.out.println("--------------------------------ALL STUDENTS:-------------------------");
            List<PersistentObject> studentList = studentDAO.query(hibernateUtils.getSession());
            System.out.println(studentList.stream()
                    .map(Student.class::cast)
                    .map(Student::toString).collect(Collectors.toSet()));
            System.out.println("----------------------------------------------------------------------");

            // query students with where:
            System.out.println("--------------------------ALL STUDENTS WHERE:-------------------------");
            List<PersistentObject> studentWhereList = studentDAO.query(hibernateUtils.getSession(), "s.lastName = 'Khaskin'");
            System.out.println(studentWhereList.stream()
                    .map(Student.class::cast)
                    .map(Student::toString).collect(Collectors.toSet()));
            System.out.println("----------------------------------------------------------------------");

            // save a new student:
            System.out.println("------------------------------SAVE A STUDENT:-------------------------");
            Student student = new Student("Albert", "Einstein", "albert@gmail.com");
            studentDAO.save(student, hibernateUtils.getSession());
            System.out.println("----------------------------------------------------------------------");

            // get created student:
            System.out.println("---------------------------GET SAVED STUDENT:-------------------------");
            Student createdStudent = (Student) studentDAO.get(student.getId(), Student.class, hibernateUtils.getSession());
            System.out.println(createdStudent.toString());
            System.out.println("----------------------------------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        }
    }

}