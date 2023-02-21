package de.dkh.springdemoaoppointcutdeclarations.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Example of an {@linkplain Aspect} using {@linkplain org.aspectj.lang.annotation.Pointcut} expressions.
 */
@Aspect
@Component
public class DemoLoggingAspectWithPCExpr {

    /**
     * This point cut expression defined as a method can be referred any time further.
     */
    @Pointcut("execution(public void de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO.addAccounts(java.util.List))")
    private void forAddAccounts() {
    }

    @Before("forAddAccounts()")
    public void beforeAddAccountsWithPredefPointCutAdvise() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#addAccounts(List<Account> accounts)");
    }

}
