package de.dkh.springdemoaoppointcutdeclarations;

import de.dkh.springdemoaoppointcutdeclarations.service.TrafficFortuneService;
import org.aspectj.lang.JoinPoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

/**
 * Applying of {@linkplain org.aspectj.lang.annotation.AfterReturning} advices.
 */
@SpringBootApplication
public class SpringdemoAopAroundApplication {

    private final static Logger LOGGER = Logger.getLogger(SpringdemoAopAroundApplication.class.getName());

    public static void main(String[] args) {

        LOGGER.info(("\nMain programm: SpringdemoAopAroundApplication"));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        final TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        LOGGER.info(("\nCalling the target method trafficFortuneService.getFortune()"));
        /**
         * Method call to measure the execution time
         * using {@linkplain de.dkh.springdemoaoppointcutdeclarations.aspect.around.AroundDemoAspect#aroundGetFortune(JoinPoint)}.
         */
        final String fortune = trafficFortuneService.getFortune();

        context.close();

    }

}
