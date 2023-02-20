package de.dkh.springdemoaop.aspect;

import de.dkh.springdemoaop.dao.AccountDAO;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Exapmle of an {@linkplain Aspect}.
 * This is where we add all of our related advices for logging.
 * Those advices are actually routines, which run before/after the actual method we call.
 */
@Aspect
@Component
public class DemoLoggingAspect {

    /**
     * Example of a {@linkplain org.aspectj.lang.annotation.Before} advise.
     * This method will auto run BEFORE {@linkplain AccountDAO#addAccount()} just by annotation!
     */
    @Before("execution(public void addAccount())")
    public void beforeAddAccountAdvise() {

        System.out.println("\n=========>> Executing @Before advise before addAccount()");
    }
}
