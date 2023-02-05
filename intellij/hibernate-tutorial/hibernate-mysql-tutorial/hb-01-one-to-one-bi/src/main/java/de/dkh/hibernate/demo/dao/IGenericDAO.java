package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import org.hibernate.Session;

import java.util.List;

public interface IGenericDAO {

    public long save(PersistentObject object, Session session);

    public PersistentObject get(long id, Class<? extends PersistentObject> entityType, Session session);

    public List<PersistentObject> query(Session session);

    public List<PersistentObject> query(Session session, String where);

    public void updateProperty(long id, Class<? extends PersistentObject> entityType, Session session, String property, String value);

    public void delete(PersistentObject object, Session session);
}
