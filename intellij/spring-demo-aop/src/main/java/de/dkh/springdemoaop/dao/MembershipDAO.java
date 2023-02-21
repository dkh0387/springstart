package de.dkh.springdemoaop.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    public void addAccount() {
        System.out.println("DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
    }
}
