package de.dkh.springsecuritydemodbbcryptlandingpage.dao;

import de.dkh.springsecuritydemodbbcryptlandingpage.entity.Authority;
import de.dkh.springsecuritydemodbbcryptlandingpage.entity.User;
import de.dkh.springsecuritydemodbbcryptlandingpage.service.AuthorityService;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDAO extends PersistanceDAO {

    public void save(Authority authority) {
        Session currentSession = getSession();
        currentSession.saveOrUpdate(authority);
    }
}
