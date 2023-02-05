package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.entity.Student;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.List;

public class StudentDAO implements IGenericDAO {
    public long save(PersistentObject object, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(object);
        session.getTransaction().commit();
        return object.getId();
    }

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final PersistentObject object = session.get(entityType, id);
        session.getTransaction().commit();
        return object;
    }

    @Override
    public List<PersistentObject> query(Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final List<PersistentObject> studentList = session.createQuery("from Student").getResultList();
        session.getTransaction().commit();
        return studentList;
    }

    @Override
    public List<PersistentObject> query(Session session, String where) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final List<PersistentObject> studentList = session.createQuery(String.format("from Student s where %s", where)).getResultList();
        session.getTransaction().commit();
        return studentList;
    }

    /**
     * Example of using reflections in Spring:
     * If we need to access properties using their getters and setters,
     * we could use instead the forBeanPropertyAccess method.
     */
    @Override
    public void updateProperty(long id, Class<? extends PersistentObject> entityType, Session session, String property, String value) {
        Student student = (Student) get(id, entityType, session);
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
     */
    @Override
    public void delete(PersistentObject object, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.delete(object);
        session.getTransaction().commit();
    }
}
