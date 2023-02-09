package de.dkh.hibernate.demo.crudexamples;

import de.dkh.hibernate.demo.utils.HibernateUtils;

/**
 * Run all in one.
 * NOTE: we need to keep {@linkplain HibernateUtils#getSessionFactoryInstance()} open in order to reuse it for each step!
 */
public class CreateAllDemo {

    public static void main(String[] args) {
        CreateInstructorDemo.main(null);
        final long instructor_id = CreateInstructorDemo.instructor_id;

        CreateCoursesDemo.main(new String[]{String.valueOf(instructor_id)});
        final String[] course_ids = CreateCoursesDemo.course_ids;

        CreateCourseReviewsDemo.main(course_ids);

        CreateCourseStudentsDemo.main(course_ids);

    }
}
