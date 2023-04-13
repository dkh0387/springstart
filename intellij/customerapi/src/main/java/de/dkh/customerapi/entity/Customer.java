package de.dkh.customerapi.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(schema = "customerapi_db", name = "customer")
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
