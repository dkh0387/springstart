package de.dkh.webcostumertracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * Aspect using for logging.
 */
@Aspect
@Component
public class CRMLoggingAspect {

    private Logger logger = Logger.getLogger(getClass().getName());

    /**
     * Pointcut exp. for {@code controller} package.
     * For any class, any method within the package.
     */
    @Pointcut("execution(* de.dkh.webcostumertracker.controller.*.*(..))")
    private void forControllerPackage() {

    }

    /**
     * Pointcut exp. for {@code service} package.
     * For any class, any method within the package.
     */
    @Pointcut("execution(* de.dkh.webcostumertracker.service.*.*(..))")
    private void forServicePackage() {

    }

    /**
     * Pointcut exp. for {@code dao} package.
     * For any class, any method within the package.
     */
    @Pointcut("execution(* de.dkh.webcostumertracker.dao.*.*(..))")
    private void forDaoPackage() {

    }

    /**
     * Suits for all except {@code entity} package.
     */
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {

    }

    /**
     * Before advice for any method in package except {@code entity}.
     */
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        //display the method we are calling
        final String method = joinPoint.getSignature().toShortString();
        logger.info("\n===>>> before(JoinPoint joinPoint) advice calling method: " + method);

        //display method arguments
        final Object[] args = joinPoint.getArgs();
        Stream.of(args).forEach(o -> logger.info("\n===>>> Argument: " + o));

    }

    /**
     * After returning advice for any method in package except {@code entity}.
     *
     * @param joinPoint
     * @param result
     */
    @AfterReturning(pointcut = "forAppFlow()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {

        //display the method we are calling
        final String method = joinPoint.getSignature().toShortString();
        logger.info("\n===>>> afterReturning(JoinPoint joinPoint, Object result) advice calling method: " + method);

        //display data returned
        logger.info("\n===>>> Result: " + result);
    }
}
