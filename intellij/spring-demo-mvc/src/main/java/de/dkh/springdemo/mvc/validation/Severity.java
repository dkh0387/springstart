package de.dkh.springdemo.mvc.validation;


import javax.validation.Payload;

/**
 * Example for using {@linkplain Payload} interface.
 * Here we define the severity of a field violates the validation. This can be added to the validator as in {@linkplain Customer#getAge()}.
 */
public class Severity {
    public interface Info extends Payload {
    }

    public interface Error extends Payload {
    }
}


