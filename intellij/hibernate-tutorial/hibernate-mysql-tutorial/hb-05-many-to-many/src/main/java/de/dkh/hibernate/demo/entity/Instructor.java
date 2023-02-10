package de.dkh.hibernate.demo.entity;

import de.dkh.hibernate.demo.crudexamples.CreateCourseReviewsDemo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Belongs to {@linkplain OneToOne} unidirectional example related to {@linkplain InstructorDetail}.
 * unidirectional means: we have only the {@linkplain InstructorDetail} relation here, not reversed.
 * NOTE: the {@linkplain FetchType#LAZY} is selected here,
 * because we want to read courses from instructor only on demand, see {@linkplain CreateCourseReviewsDemo}.
 */
@Entity
@Table(name = "instructor")
@NoArgsConstructor
@ToString
public class Instructor extends PersistentObject {
    public static EntityType ENTITY_TYPE = EntityType.INSTRUCTOR;

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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
    @OneToOne
    @JoinColumn(name = "instructor_detail_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Getter
    @Setter
    @ToString.Exclude
    private InstructorDetail instructorDetail;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = {CascadeType.MERGE
            , CascadeType.DETACH
            , CascadeType.PERSIST
            , CascadeType.REFRESH})
    @Getter
    @Setter
    @ToString.Exclude
    private List<Course> courses;

    /**
     * We do need this method to create a bidirectional relation,
     * except if we bind {@linkplain this} to the {@linkplain Course} via constructor directly.
     *
     * @param course
     */
    public void add(Course course) {

        if (courses == null) {
            courses = new ArrayList<>();
        }
        course.setInstructor(this);
        courses.add(course);
    }

}
