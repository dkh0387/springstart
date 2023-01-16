package de.dkh.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling request against homepage "http//:localhost:8080/".
 * {@linkplain Controller} extends {@linkplain org.springframework.stereotype.Component}, so it is a bean being scanned as usual.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHomepage() {
        return "main-menu";
    }
}
