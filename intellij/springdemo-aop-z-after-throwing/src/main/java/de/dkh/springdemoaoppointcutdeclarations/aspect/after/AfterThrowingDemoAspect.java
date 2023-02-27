package de.dkh.springdemoaoppointcutdeclarations.aspect.after;

import de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Examples of {@linkplain AfterThrowing} advices.
 * Those advices are applied AFTER the (unsuccessful) method execution.
 * <p>
 */
@Aspect
@Component
public class AfterThrowingDemoAspect {

    /**
     * {@linkplain AfterThrowing} advice example.
     * NOTE: the usage of returning parameter; we can access the method return from {@linkplain JoinPoint} and work with it further.
     * The name of {@code throwing} and the name of the param have to be equal,
     * AOP automatically grapes the exception in {@linkplain AccountDAO#findAccounts()} and bind it to param {@code exp}!
     *
     * @param joinPoint
     * @param exp
     */
    @AfterThrowing(pointcut = "de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forGetFromAccountList()", throwing = "exp")
    public void afterThrowingFindAdvice(JoinPoint joinPoint, Throwable exp) {

        System.out.println("\n=====> Advice: afterThrowingFindAdvice(JoinPoint joinPoint, Throwable exp)");
        System.out.println("\n=====> Exception being thrown by called method: " + exp.getLocalizedMessage());

    }

}
