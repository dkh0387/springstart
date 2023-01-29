package de.dkh.springdemo.mvc.validation;

import lombok.*;
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
    /**
     * NOTE: we have a {@linkplain NotNull} here,
     * but we can not use {@code int} anymore, because to be proven it has to be converted to {@code String}.
     */
    @Getter
    @Setter
    @NotNull(message = "is required")
    @Min(value = 0, message = "at least 0")
    @Max(value = 10, message = "not greater than 10")
    private Integer freePasses;
    @Getter
    @Setter
    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 chars/digits")
    private String postCode;
    @Getter
    @Setter
    @CourseCode(value = "LUV", message = "should start with LUV")
    private String courseCode;
    @Getter
    @Setter
    @Min(value = 18, message = "minimum age is 18", groups = CustomerPersonCheck.class, payload = Severity.Info.class)
    private Integer age;
    @Getter
    @Setter
    @NotNull(message = "is required", groups = CustomerPersonCheck.class, payload = Severity.Error.class)
    private String nationality;

}
