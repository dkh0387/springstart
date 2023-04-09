package de.dkh.springsecuritydemodbbcryptlanding.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Spring project configuration WITHOUT xml. Everything here is the replacement for {@code dispatcher-servlet.xml}.
 * {@linkplain EnableWebMvc} replaces {@code <mvc:annotation-driven/>}
 * (support for conversion, formatting and validation).
 * We are reading in the JDBC properties from the {@code persistence-mysql.properties}.
 */
@Configuration
@EnableWebMvc
@ComponentScan("de.dkh.springsecuritydemodbbcryptlanding")
@PropertySource("classpath:persistence-mysql.properties")
public class DemoConfig implements WebMvcConfigurer {

    /**
     * Set up environment variable to hold the JDBC properties.
     * This thing holds all props from {@code persistence-mysql.properties}
     * and can be used to initialized the DB connection.
     * NOTE: {@linkplain Environment} is a helper Spring class to hold those props.
     */
    @Autowired
    private Environment environment;
    private Logger logger = Logger.getLogger(getClass().getName());

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

    /**
     * Define a bean for the security data source.
     * We equip the {@linkplain ComboPooledDataSource} with props from {@linkplain this#environment}.
     */
    @Bean
    public DataSource securityDataSource() {

        // create a connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // set a JDBC driver class
        try {
            securityDataSource.setDriverClass(environment.getProperty("jdbc.driver"));
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        // log some connection props
        logger.info(">>>>> jdbc.driver: " + environment.getProperty("jdbc.driver"));
        logger.info(">>>>> jdbc.url: " + environment.getProperty("jdbc.url"));
        logger.info(">>>>> jdbc.user: " + environment.getProperty("jdbc.user"));
        logger.info(">>>>> jdbc.password: " + environment.getProperty("jdbc.password"));

        // set db connection props
        securityDataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        securityDataSource.setUser(environment.getProperty("jdbc.user"));
        securityDataSource.setPassword(environment.getProperty("jdbc.password"));

        // set connection pool props
        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    private int getIntProperty(String propName) {
        return Integer.parseInt(Objects.requireNonNull(environment.getProperty(propName)));
    }
}
