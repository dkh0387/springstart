package de.dkh.springdemo.mvc.validation;

/**
 * This is actually a group of constraints for validation of {@linkplain Customer#getAge()}.
 * This simple tagged interface allows to validate properties of {@linkplain Customer} separately:
 * we can validate all {@code Default} group props (without spec. group) just by calling {@code validator.validate(newCustomer)}
 * and an age being in {@linkplain CustomerPersonCheck} group by calling {@code validator.validate(newCustomer, CustomerPersonCheck.class)}
 * This allows us to use the validator for different contexts.
 */
public interface CustomerPersonCheck {
}
