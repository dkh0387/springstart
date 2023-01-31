package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * Why we are using JPA Annotation instead of Hibernate ?
 * <p>
 * For example, why we are not using this org.hibernate.annotations.Entity?
 * <p>
 * ANSWER:
 * JPA is a standard specification. Hibernate is an implementation of the JPA specification.
 * <p>
 * Hibernate implements all the JPA annotations.
 * <p>
 * The Hibernate team recommends the use of JPA annotations as a best practice.
 */
@Entity
@Table(name = "student")
@NoArgsConstructor
@ToString
public class Student {

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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

}
