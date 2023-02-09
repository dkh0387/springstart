package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class PersistentDAO implements IGenericDAO {

    public long save(PersistentObject object, Session session) {
        return save(object, session, true);
    }

    public long save(PersistentObject object, Session session, boolean commit) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(object);

        if (commit) {
            session.getTransaction().commit();
        }
        return object.getId();
    }

    @Override
    public void saveAll(List<PersistentObject> objectList, HibernateUtils hibernateUtils) {

        if (objectList != null) {
            objectList.forEach(o -> save(o, hibernateUtils.getSession()));
        }
    }

    public void saveAll(List<PersistentObject> objectList, HibernateUtils hibernateUtils, boolean commit) {

        if (objectList != null) {
            objectList.forEach(o -> save(o, hibernateUtils.getSession(), commit));
        }
    }

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session) {
        return get(id, entityType, session, true);
    }

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session, boolean commit) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final PersistentObject object = session.get(entityType, id);

        if (commit) {
            session.getTransaction().commit();
        }
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
