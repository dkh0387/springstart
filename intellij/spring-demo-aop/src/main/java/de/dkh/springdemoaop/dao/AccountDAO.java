package de.dkh.springdemoaop.dao;

import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    public void addAccount() {
        System.out.println("DOING MY DB WORK: ADDING AN ACCOUNT");
    }
}
