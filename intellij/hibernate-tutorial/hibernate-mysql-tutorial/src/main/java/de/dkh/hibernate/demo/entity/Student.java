package de.dkh.hibernate.demo.entity;

import de.dkh.hibernate.demo.utils.DateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
public class Student extends PersistentObject implements Serializable {

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Student(String firstName, String lastName, String email, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }


    //@GenericGenerator(name = "custom_id", strategy = "de.dkh.hibernate.demo.utils.IDGenerator")
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
    @Column(name = "date_of_birth")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", dateOfBirth=" + DateUtils.formatDate(dateOfBirth) + "]";
    }

}
