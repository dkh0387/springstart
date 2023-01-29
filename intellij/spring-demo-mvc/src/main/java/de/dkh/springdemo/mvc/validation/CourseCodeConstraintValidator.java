package de.dkh.springdemo.mvc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String valueForValidation;


    @Override
    public void initialize(CourseCode annotation) {
        valueForValidation = annotation.value();
    }

    /**
     * With {@linkplain ConstraintValidatorContext} we can add additional error messages if needed
     *
     * @param valueToValidate object to validate
     * @param context         context in which the constraint is evaluated
     * @return
     */
    @Override
    public boolean isValid(String valueToValidate, ConstraintValidatorContext context) {

        if (valueToValidate != null) {
            return valueToValidate.startsWith(valueForValidation);
        } else {
            return true;
        }
    }

}
