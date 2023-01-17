package de.dkh.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloWorldController {

    /**
     * NOTE: there is no mapping between method and view name, either between request mapping url and view name!
     * We just need to make sure the HTTP request using this url is being mappend to the right view!
     *
     * @return
     */
    @RequestMapping("/showForm")
    public String showFormView() {
        return "helloworld-form";
    }

    /**
     * NOTE: this method is actually the action of the input form in {@code helloworld-form.jsp}.
     * But the action name is NOT the method name, but the request mapping name!
     *
     * @return
     */
    @RequestMapping("/processForm")
    public String processFormView() {
        return "helloworld";
    }

    /**
     * The concept of using {@linkplain Model} here:
     * {@linkplain HttpServletRequest} is needed to get the parameter from the view (see {@code helloworld-form.jsp}).
     * {@linkplain Model} is a container, so we can add any attributes to it by name.
     * <p>
     * NOTE: this model can directly be used in the corresponding form mapping this method!
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/processFormWithModel")
    public String processFormViewWithModel(HttpServletRequest request, Model model) {

        String studentName = request.getParameter("studentName");
        model.addAttribute("name", studentName);

        return "helloworld";
    }
}
