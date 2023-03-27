package de.dkh.springsecuritydemo06csrf.controller;

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

    /**
     * Request mapping for manager page. This one is showing up after a MANAGER role logged in.
     * See: {@linkplain de.dkh.springsecuritydemo06csrf.config.DemoSecurityConfig}.
     *
     * @return
     */
    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders";
    }

    /**
     * Request mapping for manager page. This one is showing up after a ADMIN role logged in.
     * See: {@linkplain de.dkh.springsecuritydemo06csrf.config.DemoSecurityConfig}.
     *
     * @return
     */
    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }

}
