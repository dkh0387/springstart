package de.dkh.springdemoaoppointcutdeclarations.dao;

import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAO {

    public void addAccount(Account account) {
        System.out.println("CALLING addAccount(Account account)");
    }

    public void addAccounts(List<Account> accounts) {
        System.out.println("CALLING addAccounts(List<Account> accounts)");
    }

    public void updateAccount(Account account) {
        System.out.println("CALLING updateAccount(Account account)");
    }
}
