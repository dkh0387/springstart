package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;
import java.util.Map;

public interface IGenericDAO {

    public long save(PersistentObject object, Session session);

    public void saveAll(List<PersistentObject> objectList, HibernateUtils hibernateUtils);

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session);

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session, boolean commit);

    public List<? extends PersistentObject> selectAllQuery(Class<? extends PersistentObject> entityType, Session session);

    public PersistentObject selectByIdQuery(long id, Class<? extends PersistentObject> entityType, Session session);

    public List<? extends PersistentObject> query(Class<? extends PersistentObject> entityType, String hql, Map<String, Object> paramMap, Session session);

    public void updateProperty(long id, Session session, String property, String value);

    public void delete(PersistentObject object, Session session);
}
