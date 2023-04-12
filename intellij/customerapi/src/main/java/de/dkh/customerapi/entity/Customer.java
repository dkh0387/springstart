package de.dkh.customerapi.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(schema = "mysql_customerapi", name = "customer")
public class Customer extends PersistentObject implements Serializable {

    @Getter
    @Setter
    @Column
    private String firstName;

    @Getter
    @Setter
    @Column
    private String lastName;

    @Getter
    @Setter
    @Column
    private String email;

}
