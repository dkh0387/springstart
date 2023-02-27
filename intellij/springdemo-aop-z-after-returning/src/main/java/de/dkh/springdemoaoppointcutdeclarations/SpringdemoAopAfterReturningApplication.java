package de.dkh.springdemoaoppointcutdeclarations;

import de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO;
import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Applying of {@linkplain org.aspectj.lang.annotation.AfterReturning} advices.
 */
@SpringBootApplication
public class SpringdemoAopAfterReturningApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        final AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        /**
         * NOTE: the advice being defined explicitly for THIS method: {@linkplain de.dkh.springdemoaoppointcutdeclarations.aspect.after.AfterReturningDemoAspect#afterReturningFindAdviceWithSupplier(JoinPoint, List)}.
         * The returned account list is automatically bound to the param in advice itself
         * We defined the aspect applying order: first {@linkplain de.dkh.springdemoaoppointcutdeclarations.aspect.after.AfterReturningDemoWithPreprocessAspect}
         * and {@linkplain de.dkh.springdemoaoppointcutdeclarations.aspect.after.AfterReturningDemoAspect} then.
         */
        List<Account> accountList = accountDAO.findAccounts();

        context.close();

    }

}
