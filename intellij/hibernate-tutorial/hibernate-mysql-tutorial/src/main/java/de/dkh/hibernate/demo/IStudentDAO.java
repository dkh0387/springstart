package de.dkh.hibernate.demo;

import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface IStudentDAO {

    public long save(Student student, Session session);

    public Student get(long id, Session session);

    public List<Student> query(Session session);

    public List<Student> query(Session session, String where);

    public void updateProperty(long id, Session session, String property, String value);
}
