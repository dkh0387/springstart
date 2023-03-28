package de.dkh.springsecuritydemo07userroles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring project configuration WITHOUT xml. Everything here is the replacement for {@code dispatcher-servlet.xml}.
 * {@linkplain EnableWebMvc} replaces {@code <mvc:annotation-driven/>}
 * (support for conversion, formatting and validation).
 */
@Configuration
@EnableWebMvc
@ComponentScan("de.dkh.springsecuritydemo07userroles")
public class DemoConfig implements WebMvcConfigurer {

    /**
     * Define a bean for ViewResolver to find .jsp pages like:
     * <bean
     * class="org.springframework.web.servlet.view.InternalResourceViewResolver">
     * <property name="prefix" value="/WEB-INF/classes/templates/"/>
     * <property name="suffix" value=".jsp"/>
     * </bean>
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /**
     * Configure the all Java configuration to serve content from the "/css" directory.
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/css/");
    }
}
