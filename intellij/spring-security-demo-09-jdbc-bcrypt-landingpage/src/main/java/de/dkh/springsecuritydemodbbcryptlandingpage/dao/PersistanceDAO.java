package de.dkh.springsecuritydemodbbcryptlandingpage.dao;

import de.dkh.springsecuritydemodbbcryptlandingpage.config.DemoConfig;
import de.dkh.springsecuritydemodbbcryptlandingpage.entity.PersistentObject;
import de.dkh.springsecuritydemodbbcryptlandingpage.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class PersistanceDAO {

    /**
     * NOTE: this bean id corresponds directly with the one in {@linkplain DemoConfig#localSessionFactoryBean()}.
     * It allows us to define multiple data sources, session factories etc.,
     * we always have a full control over them in {@linkplain DemoConfig}.
     * NOTE: actually the bean bein cofigured in {@linkplain DemoConfig#localSessionFactoryBean()} is
     * of type {@linkplain LocalSessionFactoryBean},
     * but after we initialized the {@linkplain LocalSessionFactoryBean}, the buildSessionFactory() method was called.
     */
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
