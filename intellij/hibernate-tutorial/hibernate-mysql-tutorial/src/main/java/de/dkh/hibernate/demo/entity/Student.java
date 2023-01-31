package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

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
public class Student implements Serializable {

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    /**
     * With the generation {@linkplain GenerationType#AUTO} hibernate will look for the default {@code hibernate_sequence} table,
     * so change generation to {@linkplain GenerationType#IDENTITY} as below.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    private Long id;
//    @GenericGenerator(name = "custom_id", strategy = "de.dkh.hibernate.demo.IDGenerator")
//    @GeneratedValue(generator = "custom_id")
//    @Column(name = "custom_id", nullable = false)
//    @Getter
//    @Setter
//    private String customId;
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
