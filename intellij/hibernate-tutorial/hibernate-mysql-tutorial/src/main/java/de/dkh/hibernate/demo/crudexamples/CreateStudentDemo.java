package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.utils.DateUtils;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import de.dkh.hibernate.demo.dao.StudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;

import java.util.Date;

public class CreateStudentDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        Session session = hibernateUtils.getSession();
        StudentDAO studentDAO = new StudentDAO();

        try {
            String theDateOfBirthStr = "31/12/1998";
            Date theDateOfBirth = DateUtils.parseDate(theDateOfBirthStr);
            Student student = new Student("Melli", "Vogt", "melli@gmail.com", theDateOfBirth);
            studentDAO.save(student, session);

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }


}