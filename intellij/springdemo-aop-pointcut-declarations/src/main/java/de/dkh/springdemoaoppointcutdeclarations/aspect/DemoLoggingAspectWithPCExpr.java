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
    @Pointcut("forAdd() || forAddVIP()")
    private void forAddOrAddVIP() {
    }

    @Pointcut("!forAdd() && !forAddVIP() && forUpdate()")
    private void forUpdateAndNotForAdd() {
    }

    /**
     * Example of {@linkplain org.aspectj.lang.reflect.Advice}.
     * This one is a {@linkplain Before} advice, means it is executed BEFORE the method call.
     */
    @Before("forAddOrAddVIP()")
    public void beforeAddAccountsWithMultPredefPointCutsAdvise() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#add*Accounts(List<Account> accounts) using multiple point cut declarations");
    }

    @Before("forUpdateAndNotForAdd()")
    public void beforeAllAccountDAOExceptAddAdvise() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#updateAccount(Account account) excluding multiple point cut declarations");
    }
}
