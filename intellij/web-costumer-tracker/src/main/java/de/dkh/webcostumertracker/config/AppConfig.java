package de.dkh.webcostumertracker.config;


import de.dkh.webcostumertracker.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * 3. way defining a configuration in Spring: JAVA class.
 * Instead of using an {@code applicationContext.xml} with or without component scan we write the configuration down in this class.
 * Just like in .xml file we define the package for component scan.
 * Alternatively we can list and annotate beans explicitly here, in this case is no {@linkplain ComponentScan} needed!
 * So we do not need to annotate bean classes as {@linkplain org.springframework.stereotype.Component}.
 */
@Configuration
//@ComponentScan(basePackages = {"de.dkh.webcustomertracker"})
@PropertySource(value = {"classpath:customer.properties", "classpath:mylogger.properties"})
public class AppConfig {

    @Bean
    public MyLoggerConfig myLoggerConfig(@Value("${root.logger.level}") String rootLoggerLevel,
                                         @Value("${printed.logger.level}") String printedLoggerLevel) {

        return new MyLoggerConfig(rootLoggerLevel, printedLoggerLevel);
    }

    /**
     * Define bean for our tennis coach AND inject dependency.
     * NOTE: the method name is the bean ID of the class (here {@linkplain Customer})
     * which we call in {@linkplain de.dkh.webcostumertracker.WebCostumerTrackerApplication} (not in the bean class itself)!
     * Since the {@linkplain Customer} is not annotated as a component anymore, the bean ID is defined through this method.
     * Spring container is scanning the component using this configuration, not the {@linkplain ComponentScan} as the bean class is not annotated as {@linkplain org.springframework.stereotype.Component}!
     * NOTE: since we let the {@linkplain Scope} being singleton, any call of this method will check, if there is an instance of {@linkplain Person} already created.
     * If so, the method will not be executed, we just get th instance.
     * <p>
     * {@linkplain @Bean} annotation is reasonable by third party classes, where we can't modify the source code directly.
     * We can't simply add the @Component annotation to the source code. As a result, we need an alternative solution.
     * <p>
     * Note the setting of properties from a .properties file here:
     * {@linkplain Value} annotations with property name should be over the fields in the corresponding class {@linkplain Person}.
     * If the  bean is creating the properties are injected directly from file.
     */
    @Bean
    public Customer Customer(@Value("customer.firstName") String firstname,
                             @Value("customer.lastName") String lastName,
                             @Value("customer.email") String email) {
        return new Customer(firstname, lastName, email);
    }

}