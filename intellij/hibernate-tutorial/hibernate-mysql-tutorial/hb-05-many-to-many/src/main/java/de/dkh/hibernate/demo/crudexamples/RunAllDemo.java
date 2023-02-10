package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.dao.PersistentDAO;
import de.dkh.hibernate.demo.entity.EntityType;
import de.dkh.hibernate.demo.utils.HibernateUtils;

import java.util.stream.Stream;

/**
 * Run all in one.
 * NOTE: we need to keep {@linkplain HibernateUtils#getSessionFactoryInstance()} open in order to reuse it for each step!
 */
public class RunAllDemo {

    public static void main(String[] args) {

        HibernateUtils hibernateUtils = new HibernateUtils();
        PersistentDAO persistentDAO = new PersistentDAO();

        Stream.of(EntityType.values()).forEach(entityType -> persistentDAO.executeStatement(hibernateUtils.getSession(), entityType.getTableName(), "DELETE FROM %s"));

        CreateInstructorDetailDemo.main(null);
        final long instructor_id = CreateInstructorDetailDemo.instructor_id;

        CreateCoursesDemo.main(new String[]{String.valueOf(instructor_id)});
        final String[] course_ids = CreateCoursesDemo.course_ids;

        CreateCourseReviewsDemo.main(course_ids);

        AddStudentsForCourseDemo.main(course_ids);
        final String[] student_ids = AddStudentsForCourseDemo.student_ids;

        AddCoursesForStudent.main(student_ids);

        ReadCoursesForStudent.main(student_ids);

        DeleteCourseDemo.main(course_ids);

        DeleteStudentDemo.main(student_ids);

    }
}
