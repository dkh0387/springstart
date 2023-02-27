package de.dkh.springdemoaoppointcutdeclarations.dao;

import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountDAO {

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

    /**
     * We invoke here the {@linkplain IndexOutOfBoundsException} in order to see {@linkplain de.dkh.springdemoaoppointcutdeclarations.aspect.after.AfterThrowingDemoAspect#afterThrowingFindAdvice(JoinPoint, Throwable)} in action.
     *
     * @return
     */
    public Account getFromAccountList() {

        if (findAccounts().size() < 20) {
            throw new IndexOutOfBoundsException("The Account list has less than 20 entries!!!");
        }
        return findAccounts().get(20);
    }

}
