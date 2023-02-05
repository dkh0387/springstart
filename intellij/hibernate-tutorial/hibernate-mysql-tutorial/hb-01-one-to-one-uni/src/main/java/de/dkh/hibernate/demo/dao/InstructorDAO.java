package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import org.hibernate.Session;

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

    public PersistentObject get(long id, Session session) {
        return null;
    }

    public List<PersistentObject> query(Session session) {
        return null;
    }

    public List<PersistentObject> query(Session session, String where) {
        return null;
    }

    public void updateProperty(long id, Session session, String property, String value) {

    }
}
