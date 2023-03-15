package de.dkh.springsecuritydemo06csrf.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);

    }


    /**
     * Custom routing for self created login form.
     * NOTE: we need a controller mapping for routing in order to navigate to the view.
     * <p>
     * 1. Any request faces the login form
     * 2. we navigate to the custom login page using mapping {@code /showMyLoginPage}
     * 3. Everyone is allowed to see the login page
     * 4. NOTE: Configure Spring Security to allow unauthenticated requests (permit all)to the "/css" directory
     * 5. In Spring Security, the default logout URL is "/logout". There is auto redirection to the login page after logout,
     * all we need is to hang "/logout" action on a submit button (see {@code home.jsp})
     * 6. Pay attention to {@code .antMatchers("/leaders").hasRole("MANAGER")}:
     * after login /leaders path is being processed for MANAGER roles
     *
     * @param http
     * @return
     * @throws Exception
     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .authorizeRequests(configurer ->
//                        configurer
//                                .antMatchers("/css/**")
//                                .permitAll()
//                                .anyRequest()
//                                .authenticated()
//                )
//                .formLogin(configurer -> {
//                    try {
//                        configurer
//                                .loginPage("/showMyLoginPage")
//                                .loginProcessingUrl("/authenticateTheUser")
//                                .permitAll()
//                                .and()
//                                .logout()
//                                .permitAll();
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//
//                .build();
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeRequests(configurer ->
                        configurer
                                .antMatchers("/").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
                                .antMatchers("/leaders/**").hasRole("MANAGER")
                                .antMatchers("/systems/**").hasRole("ADMIN"))

                .formLogin(configurer ->
                        configurer
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll())

                .logout(LogoutConfigurer::permitAll)

                .build();
    }

}
