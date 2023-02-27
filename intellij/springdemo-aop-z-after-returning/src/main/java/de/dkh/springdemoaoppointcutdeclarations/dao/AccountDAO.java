package de.dkh.springdemoaoppointcutdeclarations.dao;

import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Component
public class AccountDAO {
    @Getter
    @Setter
    private Supplier<List<Account>> accountSupplier;

    public void addAccount(Account account) {
        System.out.println("CALLING addAccount(Account account)");
    }

    public void addAccounts(List<Account> accounts) {
        System.out.println("CALLING addAccounts(List<Account> accounts)");
    }

    public void updateAccount(Account account) {
        System.out.println("CALLING updateAccount(Account account)");
    }

    public List<Account> findAccounts() {
        List<Account> resultList = new ArrayList<>();
        Account account1 = new Account("Denis", "Silver");
        Account account2 = new Account("Elena", "Platinum");
        Account account3 = new Account("Mark", "Gold");
        resultList.add(account1);
        resultList.add(account2);
        resultList.add(account3);

        return resultList;
    }

}
