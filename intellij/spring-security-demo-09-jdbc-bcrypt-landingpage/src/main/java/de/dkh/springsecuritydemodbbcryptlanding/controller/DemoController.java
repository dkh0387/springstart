package de.dkh.springsecuritydemodbbcryptlanding.controller;

import de.dkh.springsecuritydemodbbcryptlanding.config.DemoSecurityConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    /**
     * Mapping for {@code landing.jsp} for the landing page.
     *
     * @return
     */
    @GetMapping("/")
    public String showHome() {
        return "landing";
    }

    /**
     * Mapping for {@code home.jsp} for the homepage.
     *
     * @return
     */
    @GetMapping("/employees")
    public String showEmployees() {
        return "home";
    }

    /**
     * Request mapping for manager page. This one is showing up after a MANAGER role logged in.
     * See: {@linkplain DemoSecurityConfig}.
     *
     * @return
     */
    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders";
    }

    /**
     * Request mapping for manager page. This one is showing up after a ADMIN role logged in.
     * See: {@linkplain DemoSecurityConfig}.
     *
     * @return
     */
    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }

}
