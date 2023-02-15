package de.dkh.webcostumertracker.controller;

import de.dkh.webcostumertracker.entity.Customer;
import de.dkh.webcostumertracker.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * DI of {@linkplain CustomerService}: Spring will scan for all implementations of {@linkplain CustomerService}
 * and since we only have {@linkplain de.dkh.webcostumertracker.service.CustomerServiceImpl} it will autowire this one.
 * If we had multiple implementations we could use {@linkplain Qualifier} (see deeper).
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    @Qualifier("customerServiceImpl")
    private CustomerService customerService;

    @GetMapping("/list")
    public String showCustomers(Model model) {
        // get the customers using CustomerService
        List<Customer> customers = customerService.getCustomers();
        // add the customers to the model
        model.addAttribute("customers", customers);
        return "list-customers";
    }

    /**
     * This is a controller mapping for showing "Add Customer" form. This one is opened if we click the button defined in {@code list-customers.jsp}.
     * We bind a new {@linkplain Customer} instance to the model. All field values from the form are being bounded to the model.
     *
     * @param model
     * @return
     */
    @GetMapping("/showFormForAdd")
    public String showAddCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());

        return "customer-form";
    }

    /**
     * Example of mapping GET-requests with parameters.
     * We retrieve the URL {@code /customer/showFormForEdit?customerId=...} and can read the {@linkplain Customer#getId()}
     * using {@linkplain RequestParam}. We can then use this id to find the {@linkplain Customer} in the db.
     * Then we bind the customer to the model and send it over to the {@code customer-form.jsp} to prefill the form.
     * <p>
     * NOTE: the name of the model attribute has EXACTLY to match with the one in {@code customer-form.jsp}!
     *
     * @param id
     * @return
     */
    @GetMapping("/showFormForEdit")
    public String showEditCustomerForm(@RequestParam("customerId") long id, Model model) {
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);
        return "customer-form";
    }

    /**
     * MVC mapping for saving a new {@linkplain Customer}.
     * The {@linkplain ModelAttribute} attribute is the one in {@link this#showAddCustomerForm(Model)}.
     * After saving the customer we are going back to the {@code customer-list.jsp}.
     * <p>
     * NOTE: we are using this mapping for both: saving a new and updating an existing customer.
     * We need to keep track on {@linkplain Customer#getId()} in order to recognize update vs. save.
     * In the background we let hibernate do the job using {@linkplain org.hibernate.Session#saveOrUpdate(Object)}.
     * For that purpose we use a hidden form with the id in {@code customer-form.jsp}.
     *
     * @param customer
     * @return
     */
    @PostMapping("/saveCustomer")
    public String processCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }
}
