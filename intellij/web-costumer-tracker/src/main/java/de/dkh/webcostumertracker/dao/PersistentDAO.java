package de.dkh.webcostumertracker.dao;


import de.dkh.webcostumertracker.entity.PersistentObject;
import de.dkh.webcostumertracker.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class PersistentDAO implements IGenericDAO {

    @Override
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

    public long save(PersistentObject object, Session session) {
        return save(object, session, true);
    }

    @Override
    public void saveOrUpdate(PersistentObject object, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.saveOrUpdate(object);
        session.getTransaction().commit();
    }

    @Override
    public void saveAll(List<PersistentObject> objectList, HibernateUtils hibernateUtils) {

        if (objectList != null) {
            objectList.forEach(o -> save(o, hibernateUtils.getSession()));
        }
    }

    @Override
    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session) {
        return get(id, entityType, session, true);
    }

    @Override
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

    @Override
    public List<PersistentObject> query(Session session) {
        return null;
    }

    @Override
    public List<PersistentObject> query(Session session, String where) {
        return null;
    }

    @Override
    public void executeStatement(Session session, String tableName, String sql) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        NativeQuery query = session.createSQLQuery(String.format(sql
                , tableName));
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
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
