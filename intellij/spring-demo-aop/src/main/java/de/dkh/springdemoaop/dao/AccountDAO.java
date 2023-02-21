package de.dkh.springdemoaop.dao;

import de.dkh.springdemoaop.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDAO {

    public void addAccount() {
        System.out.println("DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    public void addAccount(Account account) {
        System.out.println("DOING MY DB WORK: ADDING AN ACCOUNT COMING AS PARAM");
    }

    public void addAccount(Account account, boolean vipAccount) {
        System.out.println("DOING MY DB WORK: ADDING AN ACCOUNT WITH MULTIPLE EXPLICITLY PARAMS");
    }

    public void addAccount(Account account, boolean vipAccount, int number) {
        System.out.println("DOING MY DB WORK: ADDING AN ACCOUNT WITH MULTIPLE PARAMS");
    }
}
