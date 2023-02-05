package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        StudentDAO studentDAO = new StudentDAO();

        try {
            Student studentBefore = (Student) studentDAO.get(21, Student.class, hibernateUtils.getSession());
            System.out.println("Student to delete: " + studentBefore);

            studentDAO.delete(studentBefore, hibernateUtils.getSession());

            Student studentAfter = (Student) studentDAO.get(21, Student.class, hibernateUtils.getSession());
            System.out.println(studentAfter == null ? "Student deleted" : "Student still exists");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        }
    }


}