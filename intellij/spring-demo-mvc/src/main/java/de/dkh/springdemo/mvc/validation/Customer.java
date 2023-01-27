package de.dkh.springdemo.mvc.validation;

import lombok.*;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

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
    @Getter
    @Setter
    @Min(value = 0, message = "at least 0")
    @Max(value = 10, message = "not greater than 10")
    private int freePasses;
    @Getter
    @Setter
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postCode;
}
