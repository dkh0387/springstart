package de.dkh.springdemoaoppointcutdeclarations.aspect;

import de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO;
import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class DemoLoggingAspect {

    /**
     * Example of {@linkplain org.aspectj.lang.reflect.Advice}.
     * This one is a {@linkplain Before} advice, means it is executed BEFORE the method call.
     * NOTE: all below advices are being executed BEFORE {@linkplain AccountDAO#updateAccount(Account)},
     * the ORDER of execution is a priori NOT defined,
     * so we need to annotate it using {@linkplain Order}.
     * <p>
     * We refer to the {@linkplain PointCutExpressions} using fully qualified class name!
     */
    @Before("de.dkh.springdemoaoppointcutdeclarations.aspect.PointCutExpressions.forUpdateAndNotForAdd()")
    public void beforeUpdateAdvice() {

        System.out.println("\n=========>> Executing @Before advise before AccountDAO#updateAccount(Account account) excluding ALL add...() methods");
    }

}
