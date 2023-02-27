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
 */
@Aspect
@Component
@Order(2)
public class AfterReturningDemoWithPreprocessAspect {

    @AfterReturning(pointcut = "de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forFindAccounts()", returning = "resultList")
    public void afterReturningFindPreprocessAdvice(JoinPoint joinPoint, List<Account> resultList) {

        System.out.println("\n=====> Advice: AfterReturningDemoWithPreprocessAspect#afterReturningFindPreprocessAdvice(JoinPoint joinPoint, List<Account> resultList)");

        AccountDAO callerObject = (AccountDAO) joinPoint.getThis();

        System.out.println("\n=====> Caller object: " + callerObject);
        System.out.println("\n=====> Method called: " + joinPoint.getSignature().toShortString());

        System.out.println("\n=====> Result list from method BEFORE advice preprocessing: " + resultList);

        //we preprocess the list from the caller object here and let the caller return the manipulated list:
        resultList.forEach(account -> account.setName(account.getName().toUpperCase()));

        System.out.println("\n=====> Result list from method AFTER advice preprocessing: " + resultList);
    }
}
