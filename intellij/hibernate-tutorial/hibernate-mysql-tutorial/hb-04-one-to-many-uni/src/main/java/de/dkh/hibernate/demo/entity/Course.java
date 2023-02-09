package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Example of {@linkplain OneToMany} relation to {@linkplain Review}.
 * NOTE: the unidirectional relation here by using {@linkplain  JoinColumn} only (on the @OneToMany side), NO pointer in {@linkplain Review}!
 * If we switch the {@linkplain  JoinColumn} to the {@linkplain Review} and create a {@linkplain Course} property hibernate will handle it like {@linkplain ManyToMany} with an extra table!!
 * <p>
 * --> USE {@linkplain  JoinColumn} ON THE {@linkplain OneToMany} AND NO EXTRA FIELD ON THE {@linkplain ManyToOne} SIDE TO AVOID THIS!!!
 * <p>
 * In bidirectional case we would have {@linkplain  JoinColumn} in {@linkplain Review} (with a {@linkplain Course} property) AND {@linkplain OneToMany#mappedBy()} here!
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
}
