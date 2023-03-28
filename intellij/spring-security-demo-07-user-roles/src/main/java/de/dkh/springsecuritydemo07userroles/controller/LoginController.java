package de.dkh.springsecuritydemo07userroles.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to map the custom login page.
 */
@Controller
public class LoginController {

    @GetMapping("/showMyLoginPage")
    public String showLoginPage() {
        return "fancy-login";
    }

    /**
     * Request mapping for access denied custom page.
     * Redirect to this one is configured in {@linkplain de.dkh.springsecuritydemo07userroles.config.DemoSecurityConfig}.
     *
     * @return
     */
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
