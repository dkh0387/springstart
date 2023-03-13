package de.dkh.springsecuritydemo04bootstraploginform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    /**
     * Mapping for {@code home.jsp} for the homepage.
     *
     * @return
     */
    @GetMapping("/")
    public String showHome() {
        return "home";
    }
}
