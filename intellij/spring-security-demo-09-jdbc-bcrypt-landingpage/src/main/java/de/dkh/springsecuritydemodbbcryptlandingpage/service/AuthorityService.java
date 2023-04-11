package de.dkh.springsecuritydemodbbcryptlandingpage.service;

import de.dkh.springsecuritydemodbbcryptlandingpage.dao.AuthorityDAO;
import de.dkh.springsecuritydemodbbcryptlandingpage.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityDAO authorityDAO;

    @Transactional
    public void saveAuthority(Authority authority) {
        authorityDAO.save(authority);
    }
}
