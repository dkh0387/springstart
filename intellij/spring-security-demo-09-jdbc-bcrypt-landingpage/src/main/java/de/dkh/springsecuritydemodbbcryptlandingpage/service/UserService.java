package de.dkh.springsecuritydemodbbcryptlandingpage.service;

import de.dkh.springsecuritydemodbbcryptlandingpage.dao.PersistanceDAO;
import de.dkh.springsecuritydemodbbcryptlandingpage.dao.UserDAO;
import de.dkh.springsecuritydemodbbcryptlandingpage.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Transactional
    public void saveUser(User user) {
        user.setPassword(encodeEncryptUserPassword(user));
        userDAO.save(user);
    }

    public String encodeEncryptUserPassword(User user) {
        String password = user.getPassword();
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder.encode(password);
    }
}
