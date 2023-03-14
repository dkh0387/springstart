package de.dkh.springsecuritydemo06csrf.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Special class to register the Spring security filters.
 * Those filters are responsible for checking like:
 * request comes in, is user logged in?, is user allowed to see the context etc.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
}
