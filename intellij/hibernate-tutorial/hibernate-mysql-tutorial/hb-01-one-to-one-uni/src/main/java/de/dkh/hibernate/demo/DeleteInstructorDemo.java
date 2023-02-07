package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.utils.HibernateUtils;

public class DeleteInstructorDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            Instructor instructor = (Instructor) instructorDAO.get(2, Instructor.class, hibernateUtils.getSession());
            instructorDAO.delete(instructor, hibernateUtils.getSession());

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}