package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.Instructor;
import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;
import org.springframework.beans.PropertyAccessor;
import org.springframework.beans.PropertyAccessorFactory;

import java.util.List;

public class InstructorDAO implements IGenericDAO {

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

    public List<PersistentObject> query(Session session) {
        return null;
    }

    public List<PersistentObject> query(Session session, String where) {
        return null;
    }

    @Override
    public void updateProperty(long id, Class<? extends PersistentObject> entityType, Session session, String property, String value) {
        Instructor instructor = (Instructor) get(id, entityType, session);
        System.out.println("Instructor before update(): " + instructor);
        PropertyAccessor myAccessor = PropertyAccessorFactory.forBeanPropertyAccess(instructor);
        // a `setSomeProperty()` method will be used
        myAccessor.setPropertyValue(property, value);
        System.out.println("Instructor after update(): " + instructor);

        Session newSession = new HibernateUtils().getSession();

        if (!newSession.getTransaction().isActive()) {
            newSession.beginTransaction();
        }
        newSession.update(instructor);
        newSession.getTransaction().commit();
    }

    @Override
    public void delete(PersistentObject object, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.delete(object);
        session.getTransaction().commit();
    }
}
