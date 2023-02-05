package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.utils.HibernateUtils;
import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.List;

public class StudentDAO implements IGenericDAO {
    public long save(Student student, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(student);
        session.getTransaction().commit();
        return student.getId();
    }

    public Student get(long id, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public List<Student> query(Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final List<Student> studentList = session.createQuery("from Student").getResultList();
        session.getTransaction().commit();
        return studentList;
    }

    @Override
    public List<Student> query(Session session, String where) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final List<Student> studentList = session.createQuery(String.format("from Student s where %s", where)).getResultList();
        session.getTransaction().commit();
        return studentList;
    }

    /**
     * Example of using reflections in Spring:
     * If we need to access properties using their getters and setters,
     * we could use instead the forBeanPropertyAccess method.
     *
     * @param id
     * @param session
     * @param property
     * @param value
     */
    @Override
    public void updateProperty(long id, Session session, String property, String value) {
        Student student = get(id, session);
        System.out.println("Student before update(): " + student);
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(student);
        // a `setSomeProperty()` method will be used
        myAccessor.setPropertyValue(property, value);
        System.out.println("Student after update(): " + student);

        Session newSession = new HibernateUtils().getSession();

        if (!newSession.getTransaction().isActive()) {
            newSession.beginTransaction();
        }
        newSession.update(student);
        newSession.getTransaction().commit();
    }

    /**
     * Example of delete operation.
     *
     * @param student
     * @param session
     */
    public void delete(Student student, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.delete(student);
        session.getTransaction().commit();
    }
}
