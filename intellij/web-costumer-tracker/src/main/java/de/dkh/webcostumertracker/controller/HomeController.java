package de.dkh.webcostumertracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showHomePage() {
        return "redirect:/customer/list";
    }
}
