package de.dkh.springdemo.mvc.validation;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Customer {

    @Getter
    @Setter
    private String firstName;

    /**
     * Using Hibernate validation on this property.
     */
    @Getter
    @Setter
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;
}
