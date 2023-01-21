package de.dkh.springdemo.mvc;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Student {

    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;

}
