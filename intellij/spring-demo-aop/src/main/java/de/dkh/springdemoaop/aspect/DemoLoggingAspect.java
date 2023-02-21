package de.dkh.springdemoaop.aspect;

import de.dkh.springdemoaop.dao.AccountDAO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Example of an {@linkplain Aspect}.
 * This is where we add all of our related advices for logging.
 * Those advices are actually routines, which run before/after the actual method we call.
 */
@Aspect
@Component
public class DemoLoggingAspect {

    /**
     * Example of a {@linkplain org.aspectj.lang.annotation.Before} advise.
     * This method will auto run BEFORE {@linkplain AccountDAO#addAccount()} just by annotation!
     * <p>
     * NOTE: the expression within brackets calls {@code Pointcut}.
     * This is telling Spring AOP {@linkplain Before} before where advise should be executed.
     * The method is being linked ONLY if we refer with the fully classname.
     * <p>
     * We can either explicitly call the method or use wildcards (see the second example here)
     * <p>
     * If we not precise the class the advice will be executed before ANY {@code addAcoount()}.
     * <p>
     * NOTE: the first and the second advises are actually duplicated,
     * so we have TWO advises on {@linkplain AccountDAO#addAccount()}.
     */


    //@Before("execution(public void de.dkh.springdemoaop.dao.AccountDAO.add*())")
    @Before("execution(public void de.dkh.springdemoaop.dao.AccountDAO.addAccount())")
    public void beforeAddAccountSpecClassAdvise() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#addAccount()");
    }

    @Before("execution(public void addAccount())")
    public void beforeAddAccountAllClassAdvise() {

        System.out.println("\n=========>> Executing @Before advise before addAccount() for all classes");
    }

    /**
     * Example of calling all methods of ANY signature,
     * ANY return type, starts with add...
     * AND a parameter {@linkplain de.dkh.springdemoaop.entity.Account}.
     */
    @Before("execution(* de.dkh.springdemoaop.dao.AccountDAO.add*(de.dkh.springdemoaop.entity.Account))")
    public void beforeAddAccountWithParamAdvise() {

        System.out.println("\n=========>> Executing @Before advise before addAccount(Account account)");
    }

    /**
     * Example of calling all methods of ANY signature,
     * ANY return type, starts with add...
     * AND multiple explicitly parameters.
     */
    @Before("execution(* de.dkh.springdemoaop.dao.AccountDAO.add*(de.dkh.springdemoaop.entity.Account, boolean))")
    public void beforeAddAccountWithExplicitlyParamsAdvise() {

        System.out.println("\n=========>> Executing @Before advise before addAccount(Account account, true/false)");
    }

    /**
     * Example of calling all methods of ANY signature,
     * ANY return type, starts with add...
     * AND multiple parameters using wildcard.
     */
    @Before("execution(* de.dkh.springdemoaop.dao.AccountDAO.add*(..))")
    public void beforeAddAccountWithAnyParamsAdvise() {

        System.out.println("\n=========>> Executing @Before advise before addAccount(..)");
    }

    /**
     * Example of calling ALL methods of ANY signature,
     * ANY return type, starts with add...,
     * multiple parameters using wildcard
     * AND WITHIN a special package.
     */
    @Before("execution(* de.dkh.springdemoaop.dao.*.add*(..))")
    public void beforeAddAccountWithAnyParamsInPackageAdvise() {

        System.out.println("\n=========>> Executing @Before advise before ANY add*(..) in ANY class in de.dkh.springdemoaop.dao");
    }
}
