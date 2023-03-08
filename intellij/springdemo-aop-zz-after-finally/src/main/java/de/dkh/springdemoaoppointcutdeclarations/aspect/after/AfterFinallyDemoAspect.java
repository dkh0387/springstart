package de.dkh.springdemoaoppointcutdeclarations.aspect.after;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Examples of {@linkplain After} advices.
 * Those advices are applied AFTER the method execution, regardless of the result.
 * It compares to finally case by try/catch.
 * <p>
 */
@Aspect
@Component
public class AfterFinallyDemoAspect {

    /**
     * {@linkplain After} advice example.
     * NOTE: the usage of returning parameter; we can access the method return from {@linkplain JoinPoint} and work with it further.
     *
     * @param joinPoint
     */
    @After("de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forGetFromAccountList()")
    public void afterFinallyFindAdvice(JoinPoint joinPoint) {

        final String method = joinPoint.getSignature().toShortString();

        System.out.println(String.format("\n=====> Advice: afterFinallyFindAdvice(JoinPoint joinPoint called on method: %s)", method));

    }

}
