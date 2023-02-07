package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.InstructorDetail;
import de.dkh.hibernate.demo.utils.HibernateUtils;

public class ReadInstructorDetailDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            InstructorDetail instructorDetail = (InstructorDetail) instructorDAO.get(4, InstructorDetail.class, hibernateUtils.getSession());
            System.out.println("Instructor detail: " + instructorDetail);
            /*
             * We can get the associated Instructor since we use bidirectional relation.
             */
            System.out.println("Associated instructor: " + instructorDetail.getInstructor());

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}