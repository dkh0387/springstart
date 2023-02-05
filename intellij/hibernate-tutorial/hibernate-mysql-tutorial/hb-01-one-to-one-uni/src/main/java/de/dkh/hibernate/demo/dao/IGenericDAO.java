package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.entity.PersistentObject;
import org.hibernate.Session;

import java.util.List;

public interface IGenericDAO {

    public long save(PersistentObject object, Session session);

    public PersistentObject get(long id, Session session);

    public List<PersistentObject> query(Session session);

    public List<PersistentObject> query(Session session, String where);

    public void updateProperty(long id, Session session, String property, String value);
}
