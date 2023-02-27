package de.dkh.springdemoaoppointcutdeclarations.aspect.after;

import de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO;
import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Examples of {@linkplain AfterReturning} advices.
 * Those advices are applied AFTER the (successful) method execution.
 * <p>
 * NOTE: the order of aspects with {@linkplain org.aspectj.lang.annotation.AfterReturning} advices has to be reversed to the {@linkplain org.aspectj.lang.annotation.Before} advices:
 * the bigger number first!
 */
@Aspect
@Component
@Order(1)
public class AfterReturningDemoAspect {

    /**
     * {@linkplain AfterReturning} advice example.
     * NOTE: the usage of returning parameter; we can access the method return from {@linkplain JoinPoint} and work with it further.
     * The name of {@code returning} and the name of the param have to be equal,
     * AOP automatically grapes the return result of {@linkplain AccountDAO#findAccounts()} and bind it to param {@code resultList}!
     * <p>
     * NOTE: the advice is NOT forced to bei {@code void}, we can make it return any stuff we need!
     * Actually it is an example of preprocessing the data AFTER the return value coming in.
     *
     * @param joinPoint
     * @param resultList
     */
    @AfterReturning(pointcut = "de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forFindAccounts()", returning = "resultList")
    public AccountDAO afterReturningFindAdviceWithSupplier(JoinPoint joinPoint, List<Account> resultList) {

        AccountDAO callerObject = (AccountDAO) joinPoint.getThis();

        System.out.println("\n=====> Advice: AfterReturningDemoAspect#afterReturningFindAdviceWithSupplier(JoinPoint joinPoint, List<Account> resultList)");

        callerObject.setAccountSupplier(() -> resultList);

        System.out.println("\n=====> Caller object's supplier.get(): " + callerObject.getAccountSupplier().get());

        return callerObject;
    }

}
