package de.dkh.springdemoaoppointcutdeclarations.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Example of an {@linkplain Aspect} using {@linkplain org.aspectj.lang.annotation.Pointcut} expressions.
 * NOTE: {@linkplain org.aspectj.lang.annotation.Pointcut} expressions are actually methods without body,
 * if we add some logik inside it will be ignored. Those methods are just for pointcut management (reusing, combining etc.)
 * <p>
 * NOTE: there is NO need to declare this class as {@linkplain org.springframework.stereotype.Component} or as {@linkplain Aspect}!
 */
public class PointCutExpressions {

    @Pointcut("execution(* de.dkh.springdemoaoppointcutdeclarations.service.TrafficFortuneService.getFortune(..))")
    public void forGetFortune() {
    }

}
