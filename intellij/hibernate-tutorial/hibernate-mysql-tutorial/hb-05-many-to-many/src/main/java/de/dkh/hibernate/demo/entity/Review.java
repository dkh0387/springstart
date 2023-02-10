package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Example of {@linkplain javax.persistence.ManyToOne} unidirectional relation to {@linkplain Instructor}.
 * NOTE: no {@linkplain Course} field here, so we can NOT ask a {@linkplain Review} for their {@linkplain Course}!
 */
@Entity
@Table(name = "review")
@NoArgsConstructor
@ToString
public class Review extends PersistentObject {

    public static EntityType ENTITY_TYPE = EntityType.REVIEW;

    @Getter
    @Setter
    @Column(name = "comment")
    private String comment;

    public Review(String comment, Course course) {
        this.comment = comment;
        course.addReview(this);
    }

//    @Getter
//    @Setter
//    @ManyToOne(cascade = CascadeType.ALL)
//    @ToString.Exclude
//    private Course course;
}
