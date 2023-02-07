package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

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

    /**
     * Example of using HQL: {@code SELECT * FROM <table>}
     * note the string format: entity name matches the {@code entityType.getSimpleName()} (class name).
     *
     * @param entityType
     * @param session
     * @return
     */
    public List<? extends PersistentObject> selectAllQuery(Class<? extends PersistentObject> entityType, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Query<? extends PersistentObject> query = session.createQuery(String.format("SELECT i FROM %s i"
                        , entityType.getSimpleName())
                , entityType);
        List<? extends PersistentObject> resultList = query.getResultList();
        session.getTransaction().commit();
        return resultList;
    }

    public PersistentObject selectByIdQuery(long id, Class<? extends PersistentObject> entityType, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Query<? extends PersistentObject> query = session.createQuery(String.format("SELECT i FROM %s i WHERE i.id = %d"
                        , entityType.getSimpleName()
                        , id)
                , entityType);
        List<? extends PersistentObject> resultList = query.getResultList();
        session.getTransaction().commit();
        return (resultList != null && !resultList.isEmpty()) ? resultList.get(0) : null;
    }

    /**
     * Example of using parametrized HQL.
     *
     * @param entityType
     * @param hql
     * @param paramMap
     * @param session
     * @return
     */
    public List<? extends PersistentObject> query(Class<? extends PersistentObject> entityType, String hql, Map<String, Object> paramMap, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        Query<? extends PersistentObject> query = session.createQuery(hql, entityType);
        paramMap.keySet().forEach(k -> query.setParameter(k, paramMap.get(k)));
        List<? extends PersistentObject> resultList = query.getResultList();
        session.getTransaction().commit();
        return resultList;
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
