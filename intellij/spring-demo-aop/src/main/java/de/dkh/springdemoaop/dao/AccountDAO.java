package de.dkh.springdemoaop.dao;

import de.dkh.springdemoaop.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDAO {

    public void addAccount() {
        System.out.println("CALLING addAccount()");
    }

    public void addAccount(Account account) {
        System.out.println("CALLING addAccount(Account account)");
    }

    public void addAccount(Account account, boolean vipAccount) {
        System.out.println("CALLING addAccount(Account account, boolean vipAccount)");
    }

    public void addAccount(Account account, boolean vipAccount, int number) {
        System.out.println("CALLING addAccount(Account account, boolean vipAccount, int number)");
    }

    public void addAccounts(List<Account> accounts) {
        System.out.println("CALLING addAccounts(List<Account> accounts)");
    }
}
