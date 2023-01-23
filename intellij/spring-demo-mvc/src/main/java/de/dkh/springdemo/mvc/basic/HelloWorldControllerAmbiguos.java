package de.dkh.springdemo.mvc.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller is actually the ambiguos one to the {@linkplain HelloWorldController}.
 * Means {@linkplain this#processFormViewAmbiguos()} points to the same view,
 * so we get the {@linkplain org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer.AmbiguousBindingException} here.
 */
@Controller
public class HelloWorldControllerAmbiguos {

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
    public String processFormViewAmbiguos() {
        return "helloworld";
    }

}
