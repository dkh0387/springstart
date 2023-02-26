package de.dkh.springdemoaoppointcutdeclarations.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class CloudLogAsyncAspect {



    @Before("de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forUpdateAndNotForAdd()")
    public void logToCloudAsync() {

        System.out.println("\n=========>> Executing @Before advise logToCloudAsync()");
    }
}
