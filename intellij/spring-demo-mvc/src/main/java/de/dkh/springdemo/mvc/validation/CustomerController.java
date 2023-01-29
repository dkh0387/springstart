package de.dkh.springdemo.mvc.validation;

import de.dkh.springdemo.mvc.formtags.Student;
import org.hibernate.validator.HibernateValidatorFactory;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
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
     *
     * @param customer
     * @param bindingResult
     * @return
     */
    @RequestMapping("/processForm")
    public String processCustomerForm(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "customer-form";
        }
        System.out.println("Customer confirmed: " + customer.toString());
        return "customer-confirmation";
    }

    private boolean isValid(Customer customer) {
        final Validator validatorDEU = createValidatorFor("DEU");
        final Set<ConstraintViolation<Customer>> validate = validatorDEU.validate(customer, CustomerPersonCheck.class);
        return false;
    }

    private Validator createValidatorFor(String countryCode) {
        HibernateValidatorFactory hibernateValidatorFactory = Validation.byDefaultProvider()
                .configure()
                .buildValidatorFactory()
                .unwrap(HibernateValidatorFactory.class);

        switch (countryCode) {
            case "US":
                return hibernateValidatorFactory.usingContext()
                        .constraintValidatorPayload("US")
                        .getValidator();
            case "DEU":
                return hibernateValidatorFactory.usingContext()
                        .constraintValidatorPayload("DEU")
                        .getValidator();
            default:
                return null;
        }
    }

}
