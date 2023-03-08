package de.dkh.springdemoaoppointcutdeclarations;

import de.dkh.springdemoaoppointcutdeclarations.aspect.around.AroundHandleExceptDemoAspect;
import de.dkh.springdemoaoppointcutdeclarations.service.TrafficFortuneService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

/**
 * Applying of {@linkplain org.aspectj.lang.annotation.Around} advices with exception handling.
 */
@SpringBootApplication
public class SpringDemoAopAroundHandleExceptApplication {

    private final static Logger LOGGER = Logger.getLogger(SpringDemoAopAroundHandleExceptApplication.class.getName());

    public static void main(String[] args) {

        LOGGER.info(("\nMain programm: SpringDemoAopAroundHandleExceptApplication"));

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        final TrafficFortuneService trafficFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);

        LOGGER.info(("\nCalling the target method trafficFortuneService.getFortune(boolean tripWire)"));
        /**
         * Method call to handle the exception being thrown by the target method
         * using {@linkplain AroundHandleExceptDemoAspect#aroundGetFortune(ProceedingJoinPoint)}.
         *
         * NOTE: to see in action uncomment the advice in {@linkplain AroundHandleExceptDemoAspect}.
         */
        boolean tripWire = true;
        final String fortune = trafficFortuneService.getFortune(tripWire);

        LOGGER.info(fortune);

        context.close();

    }

}
