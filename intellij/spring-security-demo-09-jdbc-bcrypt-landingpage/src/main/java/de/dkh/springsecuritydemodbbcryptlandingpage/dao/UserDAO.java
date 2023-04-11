package de.dkh.springsecuritydemodbbcryptlandingpage.dao;

import de.dkh.springsecuritydemodbbcryptlandingpage.config.DemoConfig;
import de.dkh.springsecuritydemodbbcryptlandingpage.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class UserDAO extends PersistanceDAO {

    public void save(User user) {
        Session currentSession = getSession();
        currentSession.saveOrUpdate(user);
    }

}
