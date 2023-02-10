package de.dkh.hibernate.demo.entity;

import de.dkh.hibernate.demo.crudexamples.AddStudentsForCourseDemo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of {@linkplain ManyToMany} relation to {@linkplain Course}.
 * By saving {@linkplain Student} objects on a {@linkplain Course} it is enough to add them to the course {@linkplain Course#addStudent(Student)}.
 * After that we just have to save students {@linkplain AddStudentsForCourseDemo#main(String[])}.
 */
@Entity
@Table(name = "student")
@NoArgsConstructor
@ToString
public class Student extends PersistentObject {
    public static EntityType ENTITY_TYPE = EntityType.STUDENT;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String firstName, String lastName, String email, Course course) {
        this(firstName, lastName, email);
        course.addStudent(this);
    }

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;
    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;
    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE
            , CascadeType.DETACH
            , CascadeType.PERSIST
            , CascadeType.REFRESH})
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
    @Getter
    @Setter
    @ToString.Exclude
    private List<Course> courses;


    public void addCourse(Course course) {

        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }
}
