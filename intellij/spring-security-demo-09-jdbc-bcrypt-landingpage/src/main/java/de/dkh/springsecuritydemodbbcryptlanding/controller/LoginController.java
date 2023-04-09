package de.dkh.springsecuritydemodbbcryptlanding.controller;

import de.dkh.springsecuritydemodbbcryptlanding.config.DemoSecurityConfig;
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
     * Redirect to this one is configured in {@linkplain DemoSecurityConfig}.
     *
     * @return
     */
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "access-denied";
    }
}
