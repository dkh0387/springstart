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
    @Pointcut("execution(* de.dkh.springdemoaoppointcutdeclarations.dao.*.add*(java.util.List))")
    private void forAdd() {
    }

    @Pointcut("execution(* de.dkh.springdemoaoppointcutdeclarations.dao.*.addVIP*(java.util.List))")
    private void forAddVIP() {
    }

    @Pointcut("execution(* de.dkh.springdemoaoppointcutdeclarations.dao.*.update*(de.dkh.springdemoaoppointcutdeclarations.entity.Account))")
    private void forUpdate() {
    }

    /**
     * Combining two pointcut expressions.
     * Any other logical operation is possible: `&&`, `||`, `!`.
     */
    @Before("forAdd() || forAddVIP()")
    public void beforeAddAccountsWithMultPredefPointCutsAdvise() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#add*Accounts(List<Account> accounts) using multiple point cut declarations");
    }

    @Before("!forAdd() && !forAddVIP() && forUpdate()")
    public void beforeAllAccountDAOExceptAddAdvise() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#updateAccount(Account account) excluding multiple point cut declarations");
    }
}
