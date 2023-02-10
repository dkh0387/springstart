package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import de.dkh.hibernate.demo.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public interface IGenericDAO {

    public long save(PersistentObject object, Session session, boolean commit);

    public void saveOrUpdate(PersistentObject object, Session session);

    public void saveAll(List<PersistentObject> objectList, HibernateUtils hibernateUtils);

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session);

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session, boolean commit);

    public List<PersistentObject> query(Session session);

    public List<PersistentObject> query(Session session, String where);

    public void executeStatement(Session session, String tableName, String sql);

    public void updateProperty(long id, Session session, String property, String value);

    public void delete(PersistentObject object, Session session);
}
