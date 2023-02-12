package de.dkh.springdemo.mvc.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String showStartPage() {
        return "main-menu";
    }
}
