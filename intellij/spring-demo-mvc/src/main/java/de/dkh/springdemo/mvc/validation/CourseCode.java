package de.dkh.springdemo.mvc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation:
 * - note the @interface
 * - binding the class containing the validation logic {@linkplain CourseCodeConstraintValidator}
 * - where we can apply this annotation (methods and fields)
 * - how long the annotation is being stored in cache (during runtime)
 * - methods are according to the values we set in as parameter: {@code @CourseCode(value=..., message=...)}
 */
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    public String value() default "LUV";

    public String message() default "Should start with LUV";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
