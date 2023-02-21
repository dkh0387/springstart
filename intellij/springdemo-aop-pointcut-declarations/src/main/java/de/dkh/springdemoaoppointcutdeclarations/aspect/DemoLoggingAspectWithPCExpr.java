package de.dkh.springdemoaoppointcutdeclarations.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Example of an {@linkplain Aspect} using {@linkplain org.aspectj.lang.annotation.Pointcut} expressions.
 * NOTE: {@linkplain org.aspectj.lang.annotation.Pointcut} expressions are actually methods without body,
 * if we add some logik inside it will be ignored. Those methods are just for pointcut management (reusing, combining etc.)
 */
@Aspect
@Component
public class DemoLoggingAspectWithPCExpr {

    /**
     * This point cut expression defined as a method can be referred any time further.
     */
    @Pointcut("execution(public void de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO.add*(java.util.List))")
    private void forAddAccounts() {
    }

    @Pointcut("execution(public void de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO.addVIP*(java.util.List))")
    private void forAddVIPAccounts() {
    }

    /**
     * Combining two pointcut expressions.
     * Any other logical operation is possible: `&&`, `||`, `!`.
     */
    @Before("forAddAccounts() || forAddVIPAccounts()")
    public void beforeAddAccountsWithMultPredefPointCutsAdvise() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#add*Accounts(List<Account> accounts) using multiple point cut declarations");
    }
}
