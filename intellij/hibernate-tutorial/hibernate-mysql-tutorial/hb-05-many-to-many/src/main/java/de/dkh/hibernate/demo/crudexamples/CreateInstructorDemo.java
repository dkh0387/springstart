package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.entity.InstructorDetail;
import de.dkh.hibernate.demo.utils.HibernateUtils;

public class CreateInstructorDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        try {
            Instructor instructor = new Instructor("Denis", "Khaskin", "denis@gmail.com");
            InstructorDetail instructorDetail = new InstructorDetail("dkh@yt", "coding");
            instructor.setInstructorDetail(instructorDetail);
            /*
             * NOTE: we do have the cascade type `PERSIST` here,
             *  so we just need to bind instructorDetail to instructor and save the last one.
             */
            persistentDAO.save(instructor, hibernateUtils.getSession());

        } catch (Exception e) {
            e.printStackTrace();
            hibernateUtils.getSession().getTransaction().rollback();
        } finally {
            hibernateUtils.getSession().close();
            hibernateUtils.getSessionFactoryInstance().close();
        }
    }


}