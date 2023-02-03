package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Belongs to {@linkplain OneToOne} example related to {@linkplain InstructorDetail}.
 */
@Entity
@Table(name = "instructor")
@NoArgsConstructor
@ToString
public class Instructor implements Serializable {

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
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
    private InstructorDetail instructorDetail;

}
