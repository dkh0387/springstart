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
     */

    //@Before("execution(public void addAccount())")
    //@Before("execution(public void de.dkh.springdemoaop.dao.AccountDAO.add*())")
    @Before("execution(public void de.dkh.springdemoaop.dao.AccountDAO.addAccount())")
    public void beforeAddAccountAdvise() {

        System.out.println("\n=========>> Executing @Before advise before addAccount()");
    }
}
