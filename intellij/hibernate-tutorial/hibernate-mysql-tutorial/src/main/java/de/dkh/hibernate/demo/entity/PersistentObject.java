package de.dkh.hibernate.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public class PersistentObject {
    /**
     * With the generation {@linkplain GenerationType#AUTO} hibernate will look for the default {@code hibernate_sequence} table,
     * so change generation to {@linkplain GenerationType#IDENTITY} as below.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Getter
    @Setter
    protected Long id;
}
