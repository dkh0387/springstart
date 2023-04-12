package de.dkh.jacksondatabindingjsondemo01procesingjson.jackson.json.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * POJO, which we use to test mapping in jackson between JSON and POJO.
 * This one is corresponding to the JSON @code sample-lite.json}.
 */
@NoArgsConstructor
@ToString
public class Student {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private boolean active;

    public Student(int id, String firstName, String lastName, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.active = active;
    }

}
