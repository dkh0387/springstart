package de.dkh.springdemoaoppointcutdeclarations;

import de.dkh.springdemoaoppointcutdeclarations.service.TrafficFortuneService;
import org.aspectj.lang.JoinPoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Applying of {@linkplain org.aspectj.lang.annotation.AfterReturning} advices.
 */
@SpringBootApplication
public class SpringdemoAopAroundApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        final TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        /**
         * Method call to measure the execution time
         * using {@linkplain de.dkh.springdemoaoppointcutdeclarations.aspect.around.AroundDemoAspect#aroundGetFortune(JoinPoint)}.
         */
        final String fortune = trafficFortuneService.getFortune();

        context.close();

    }

}
