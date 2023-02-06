package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.utils.HibernateUtils;
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

    @Override
    public void saveAll(List<PersistentObject> objectList, HibernateUtils hibernateUtils) {

        if (objectList != null) {
            objectList.forEach(o -> save(o, hibernateUtils.getSession()));
        }
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

    public void updateProperty(long id, Session session, String property, String value) {

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
