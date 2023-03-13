package de.dkh.springsecuritydemo03customloginform.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This class is a replacement of {@code web.xml}.
 * We point to the {@linkplain DemoConfig}, which replaces {@code dispatcher-servlet.xml}.
 */
public class SpringMVCDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * We refer to {@linkplain DemoConfig} class here like in {@code web.xml} version:
     * <servlet>
     * <servlet-name>dispatcher</servlet-name>
     * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
     * <init-param>
     * <param-name>contextConfigLocation</param-name>
     * <param-value>classpath:dispatcher-servlet.xml</param-value>
     * </init-param>
     * <load-on-startup>1</load-on-startup>
     * </servlet>
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DemoConfig.class};
    }

    /**
     * In {@code web.xml} it goes like:
     * <p>
     * <servlet-mapping>
     * <servlet-name>dispatcher</servlet-name>
     * <url-pattern>/</url-pattern>
     * </servlet-mapping>
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
