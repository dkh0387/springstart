package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.entity.InstructorDetail;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        Session session = hibernateUtils.getSession();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            Instructor instructor = new Instructor("Denis", "Khaskin", "denis@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("dkh@yt", "coding");
            instructor.setInstructorDetail(instructorDetail);
            /*
             * NOTE: we do have the cascade type `ALL` here,
             *  so we just need to bind instructorDetail to instructor and save the last one.
             */
            instructorDAO.save(instructor, session);

        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }


}