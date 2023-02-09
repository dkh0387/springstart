package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of {@linkplain ManyToMany} relation to {@linkplain Student}.
 * NOTE: the perspectives of {@linkplain JoinTable#joinColumns()} and {@linkplain JoinTable#inverseJoinColumns()} are:
 * we consider as "joinColumns" always the foreign key in the M:N table pointing to the id of the current class (here: Course <-> course_id)!
 */
@Entity
@Table(name = "course")
@NoArgsConstructor
@ToString
public class Course extends PersistentObject {
    public Course(String title, Instructor instructor) {
        this.title = title;
        this.instructor = instructor;
    }

    @Getter
    @Setter
    @Column(name = "title")
    private String title;
    @ManyToOne(cascade = {CascadeType.MERGE
            , CascadeType.DETACH
            , CascadeType.PERSIST
            , CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id")
    @Getter
    @Setter
    @ToString.Exclude
    private Instructor instructor;
    @Getter
    @Setter
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @ToString.Exclude
    private List<Review> reviews;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE
            , CascadeType.DETACH
            , CascadeType.PERSIST
            , CascadeType.REFRESH})
    @JoinTable(name = "course_student", joinColumns = @JoinColumn(name = "course_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    @Getter
    @Setter
    @ToString.Exclude
    private List<Student> students;

    /**
     * This method is REQUIRED, when we use unidirectional relation!
     * Since we do NOT use {@linkplain OneToMany#mappedBy()} here, we need to explicitly bind {@linkplain Review} objects to the course (see {@linkplain Review#Review(String, Course)}).
     *
     * @param review
     */
    public void addReview(Review review) {

        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void addStudent(Student student) {

        if (students == null) {
            students = new ArrayList<>();
        }
        students.add(student);
    }
}
