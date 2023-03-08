package de.dkh.springdemoaoppointcutdeclarations.aspect.around;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

/**
 * Examples of {@linkplain Around} advices handling exceptions.
 * Those advices are applied BEFORE AND AFTER the method execution.
 * <p>
 * In order to synchronize the message outputs we use {@linkplain Logger} to send all messages to the same output stream.
 */
@Aspect
@Component
public class AroundHandleExceptDemoAspect {

    private final static Logger LOGGER = Logger.getLogger(AroundHandleExceptDemoAspect.class.getName());

    /**
     * {@linkplain Around} advice example.
     * Here is the use case of handling exceptions from target method demonstrated.
     * {@linkplain ProceedingJoinPoint} allows us to simulate the method call using {@code proceed()}.
     * <p>
     * NOTE: the main app will never know about exception being thrown, because we catch it within the advice!
     * It can be very dangerous!
     *
     * @param proceedingJoinPoint
     */
/*    @Around("de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forGetFortune()")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Object result = null;

        LOGGER.info("Around advice aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) calling on method: " + proceedingJoinPoint.getSignature().toShortString());

        try {
            result = proceedingJoinPoint.proceed();
        } catch (RuntimeException e) {
            LOGGER.warning(e.getLocalizedMessage());
            result = "Fortune message despite the thrown exception!";
        }
        return result;
    }*/

}
