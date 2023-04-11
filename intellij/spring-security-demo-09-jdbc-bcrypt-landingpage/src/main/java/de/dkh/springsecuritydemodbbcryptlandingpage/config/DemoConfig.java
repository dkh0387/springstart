package de.dkh.springsecuritydemodbbcryptlandingpage.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Spring project configuration WITHOUT xml. Everything here is the replacement for {@code dispatcher-servlet.xml}.
 * {@linkplain EnableWebMvc} replaces {@code <mvc:annotation-driven/>}
 * (support for conversion, formatting and validation).
 * We are reading in the JDBC properties from the {@code persistence-mysql.properties}.
 * We also define the {@linkplain SessionFactory} here for db queries.
 */
@Configuration
@EnableWebMvc
@ComponentScan("de.dkh.springsecuritydemodbbcryptlandingpage")
@PropertySource("classpath:persistence-mysql.properties")
@EnableTransactionManagement
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

    /**
     * Setup Hibernate session factory for db connection. This one is from {@code dispatcher_servlet.xml},
     * when we would use it:
     * <bean id="sessionFactory"
     * class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
     * <property name="dataSource" ref="dataSourceCustomer"/>
     * <property name="packagesToScan" value="de.dkh.springsecuritydemodbbcryptlandingpage.entity"/>
     * <property name="hibernateProperties">
     * <props>
     * <prop key="hibernate.dialect">org.hibernate.dialect.MySQLDialect</prop>
     * <prop key="hibernate.show_sql">true</prop>
     * </props>
     * </property>
     * </bean>
     *
     * @return
     */
    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean() {
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(securityDataSource());
        localSessionFactoryBean.setPackagesToScan("de.dkh.springsecuritydemodbbcryptlandingpage.entity");

        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", environment.getProperty("hibernate.dialect"));
        hibernateProperties.put("hibernate.show_sql", environment.getProperty("hibernate.show_sql"));

        localSessionFactoryBean.setHibernateProperties(hibernateProperties);

        return localSessionFactoryBean;
    }

    /**
     * Setup Hibernate transaction manager.This one is from {@code dispatcher_servlet.xml},
     * when we would use it:
     * <bean id="transactionManager"
     * class="org.springframework.orm.hibernate5.HibernateTransactionManager">
     * <property name="sessionFactory" ref="sessionFactory"/>
     * </bean>
     * <p>
     * We need this for using {@linkplain org.springframework.transaction.annotation.Transactional}
     * in dao methods.
     *
     * @return
     */
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(localSessionFactoryBean().getObject());

        return transactionManager;
    }

    private int getIntProperty(String propName) {
        return Integer.parseInt(Objects.requireNonNull(environment.getProperty(propName)));
    }
}
