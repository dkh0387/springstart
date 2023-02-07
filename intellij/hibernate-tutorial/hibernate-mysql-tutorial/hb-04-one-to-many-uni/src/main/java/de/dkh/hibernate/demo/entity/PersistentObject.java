package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Example of a super class for all entities having {@code ID} attribute.
 * To make it work we need to annotate the class with {@linkplain MappedSuperclass} in order
 * to tell hibernate to search for the id property in the super class.
 */
@MappedSuperclass
public class PersistentObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    protected Long id;
}
