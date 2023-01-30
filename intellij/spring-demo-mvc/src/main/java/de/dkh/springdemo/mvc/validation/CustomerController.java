package de.dkh.springdemo.mvc.validation;

import org.hibernate.validator.HibernateValidatorFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.*;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    /**
     * String preprocessing:
     * white spaces are being passed through the validator {@linkplain Customer#getLastName()}.
     * This thing trims white spaces to {@code null}.
     * This method will be calling for ANY web request comming in to this controller and process strings.
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping("/showForm")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    /**
     * Example of using Hibernate validation results server side.
     * The result of the validation is being stored in {@linkplain BindingResult}.
     * <p>
     * When performing Spring MVC validation, the location of the BindingResult parameter is very important.
     * In the method signature, the BindingResult parameter must appear immediately after the model attribute.
     * <p>
     * Additionally, we can explicitly prove fields associated with specific groups like {@linkplain CustomerPersonCheck}
     * using {@linkplain Payload} implementation.
     *
     * @param customer
     * @param bindingResult
     * @return
     */
    @RequestMapping("/processForm")
    public String processCustomerForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        final Set<ConstraintViolation<Customer>> validationInfoResults = validateInfo(customer);
        final Set<Class<? extends Payload>> payloads = getPayloads(validationInfoResults);


        if (bindingResult.hasErrors()) {
            return "customer-form";
        }
        System.out.println("Customer confirmed: " + customer.toString());
        return "customer-confirmation";
    }

    /**
     * Extracting {@linkplain Payload} information. Are there any fields not passed,
     * which results in {@linkplain Severity.Error} or {@linkplain Severity.Info}?
     *
     * @param validationInfoResults
     * @return
     */
    private static Set<Class<? extends Payload>> getPayloads(Set<ConstraintViolation<Customer>> validationInfoResults) {
        final Set<Class<? extends Payload>> payloads = validationInfoResults.stream()
                .map(cv -> cv.getConstraintDescriptor().getPayload())
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        return payloads;
    }

    /**
     * Usage of {@linkplain Validator} instances to validate fields of {@linkplain Customer},
     * which belong to the group {@linkplain CustomerPersonCheck}.
     *
     * @param customer
     * @return
     */
    private Set<ConstraintViolation<Customer>> validateInfo(Customer customer) {
        final Validator validatorInfo = createValidatorForInfo();
        final Set<ConstraintViolation<Customer>> violations = validatorInfo.validate(customer, CustomerPersonCheck.class);
        System.out.println(violations);
        return violations;
    }

    private Set<ConstraintViolation<Customer>> validateError(Customer customer) {
        final Validator validatorError = createValidatorForError();
        final Set<ConstraintViolation<Customer>> violations = validatorError.validate(customer, CustomerPersonCheck.class);
        System.out.println(violations);
        return violations;
    }

    /**
     * Creating {@linkplain Validator} instances based on implementations of {@linkplain Payload}.
     * Here we differ between {@linkplain Severity.Error} and {@linkplain Severity.Info} in order to separate a grave mistake.
     *
     * @return
     */
    private Validator createValidatorForError() {
        HibernateValidatorFactory hibernateValidatorFactory = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .unwrap(HibernateValidatorFactory.class);

        return hibernateValidatorFactory.usingContext()
                .constraintValidatorPayload(Severity.Error.class)
                .getValidator();
    }

    private Validator createValidatorForInfo() {
        HibernateValidatorFactory hibernateValidatorFactory = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .unwrap(HibernateValidatorFactory.class);

        return hibernateValidatorFactory.usingContext()
                .constraintValidatorPayload(Severity.Info.class)
                .getValidator();
    }

}
