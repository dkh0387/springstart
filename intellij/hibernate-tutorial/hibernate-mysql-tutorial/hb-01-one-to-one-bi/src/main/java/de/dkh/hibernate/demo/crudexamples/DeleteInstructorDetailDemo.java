package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.InstructorDetail;
import de.dkh.hibernate.demo.utils.HibernateUtils;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            InstructorDetail instructorDetail = (InstructorDetail) instructorDAO.get(2, InstructorDetail.class, hibernateUtils.getSession());
            System.out.println("Instructor detail: " + instructorDetail);

            instructorDAO.delete(instructorDetail, hibernateUtils.getSession());

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}