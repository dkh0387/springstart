package de.dkh.springsecuritydemodbbcryptlandingpage.controller;

import de.dkh.springsecuritydemodbbcryptlandingpage.entity.Authority;
import de.dkh.springsecuritydemodbbcryptlandingpage.entity.User;
import de.dkh.springsecuritydemodbbcryptlandingpage.service.AuthorityService;
import de.dkh.springsecuritydemodbbcryptlandingpage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserController {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    /**
     * Mapping for the route {@code "/newUser"}.
     */
    @GetMapping("/newUser")
    public String showNewUserFor(Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping("/saveUser")
    public String processCustomer(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        logger.info(">>>> New User: " + user.toString());

        List<Authority> authorityList = createAuthoritiesFor(user);
        logger.info(">>>> User roles: " + authorityList.toString());

        authorityList.forEach(authority -> authorityService.saveAuthority(authority));

        return "redirect:/";
    }

    private List<Authority> createAuthoritiesFor(User user) {
        List<Authority> resultList = new ArrayList<>();

        for (int i = 0; i < user.getRoles().length; i++) {
            Authority authority = new Authority(user, i);
            resultList.add(authority);
        }
        return resultList;
    }

}