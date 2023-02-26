package de.dkh.springdemoaoppointcutdeclarations.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class ApiAnalyticsAspect {

    @Before("de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forUpdateAndNotForAdd()")
    public void performApiAnalytics() {

        System.out.println("\n=========>> Executing @Before advise performApiAnalytics()");
    }
}
