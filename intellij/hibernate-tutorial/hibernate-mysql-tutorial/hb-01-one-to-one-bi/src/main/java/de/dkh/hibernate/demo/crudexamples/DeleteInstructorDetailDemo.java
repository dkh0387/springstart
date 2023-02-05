package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.InstructorDAO;
import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.entity.InstructorDetail;
import de.dkh.hibernate.demo.utils.HibernateUtils;

public class DeleteInstructorDetailDemo {
    public static void main(String[] args) {
        HibernateUtils hibernateUtils = new HibernateUtils();
        InstructorDAO instructorDAO = new InstructorDAO();

        try {
            InstructorDetail instructorDetail = (InstructorDetail) instructorDAO.get(4, InstructorDetail.class, hibernateUtils.getSession());
            System.out.println("Instructor detail: " + instructorDetail);
            /*
            We need to break the bidirectional link from instructor to detail,
             otherwise after delete of detail we will still have the not ex. foreign key in instructor table!
             */
            Instructor instructor = instructorDetail.getInstructor();
            instructorDAO.updateProperty(instructor.getId(), Instructor.class, hibernateUtils.getSession(), "instructorDetail", null);
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