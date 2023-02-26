package de.dkh.springdemoaoppointcutdeclarations.aspect;

import de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO;
import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Example of using {@linkplain org.aspectj.lang.JoinPoint}.
 * We can read all infos about a method (signature, params etc.), for what we are calling the given advice.
 */
@Aspect
@Component
@Order(1)
public class DemoLoggingAspectWithJoinPoint {

    /**
     * Example of {@linkplain org.aspectj.lang.reflect.Advice}.
     * This one is a {@linkplain Before} advice, means it is executed BEFORE the method call.
     * NOTE: all below advices are being executed BEFORE {@linkplain AccountDAO#updateAccount(Account)},
     * the ORDER of execution is a priori NOT defined,
     * so we need to annotate it using {@linkplain Order}.
     * <p>
     * We refer to the {@linkplain PointCutExpressions} using fully qualified class name!
     * <p>
     * We can explicitly access method params within the advice to apply further business logic!
     */
    @Before("de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forUpdateAndNotForAdd()")
    public void beforeUpdateAdvice(JoinPoint joinPoint) {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#updateAccount(Account account) excluding ALL add...() methods");

        //object, which executes the method:
        AccountDAO accountDAO = (AccountDAO) joinPoint.getThis();
        System.out.println("\n=========>> Object calling the method: " + accountDAO);
        //method signature:
        System.out.println("\n=========>> Advice related method signature: " + joinPoint.getSignature());
        //method params:
        Stream.of(joinPoint.getArgs()).forEach(o -> {
            Account account = (Account) o;
            System.out.println("\n=========>> Advice related method param: " + account.toString());
        });
    }

}
