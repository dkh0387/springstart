package de.dkh.springdemoaoppointcutdeclarations.aspect.around;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Examples of {@linkplain Around} advices.
 * Those advices are applied BEFORE AND AFTER the method execution.
 * <p>
 */
@Aspect
@Component
public class AroundDemoAspect {

    /**
     * {@linkplain Around} advice example.
     * Here is the use case of measure method execution time demonstrated.
     * {@linkplain ProceedingJoinPoint} allows us to simulate the method call using {@code proceed()}.
     *
     * @param proceedingJoinPoint
     */
    @Around("de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forGetFortune()")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        System.out.println("Around advice aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) calling on method: " + proceedingJoinPoint.getSignature().toShortString());

        final long begin = System.currentTimeMillis();
        final Object result = proceedingJoinPoint.proceed();
        final long end = System.currentTimeMillis();

        System.out.println("Method execution time in s: " + (end - begin) / 1000.0);

        return result;
    }

}
