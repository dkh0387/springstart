package de.dkh.hibernate.demo.dao;

import de.dkh.hibernate.demo.HibernateUtils;
import de.dkh.hibernate.demo.IStudentDAO;
import de.dkh.hibernate.demo.entity.Student;
import org.hibernate.Session;

import java.util.List;

public class StudentDAO implements IStudentDAO {
    public long save(Student student, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        session.save(student);
        session.getTransaction().commit();
        return student.getId();
    }

    public Student get(long id, Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        return student;
    }

    @Override
    public List<Student> query(Session session) {

        if (!session.getTransaction().isActive()) {
            session.beginTransaction();
        }
        final List<Student> studentList = session.createQuery("from Student").getResultList();
        session.getTransaction().commit();
        return studentList;
    }
}
