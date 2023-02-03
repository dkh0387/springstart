package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;

public class DeleteStudentDemo {
    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        StudentDAO studentDAO = new StudentDAO();

        try {
            Student studentBefore = studentDAO.get(19, hibernateUtils.getSession());
            System.out.println("Student to delete: " + studentBefore);

            studentDAO.delete(studentBefore, hibernateUtils.getSession());

            Student studentAfter = studentDAO.get(19, hibernateUtils.getSession());
            System.out.println(studentAfter == null ? "Student deleted" : "Student still exists");

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        }
    }


}