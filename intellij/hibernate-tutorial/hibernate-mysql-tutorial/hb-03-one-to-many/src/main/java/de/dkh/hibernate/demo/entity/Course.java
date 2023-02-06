package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Example of {@linkplain javax.persistence.ManyToOne} relation to {@linkplain Instructor}.
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
}
