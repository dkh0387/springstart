package de.dkh.springsecuritydemo03customloginform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to map the custom login page.
 */
@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showLoginPage() {
        return "plain-login";
    }
}
