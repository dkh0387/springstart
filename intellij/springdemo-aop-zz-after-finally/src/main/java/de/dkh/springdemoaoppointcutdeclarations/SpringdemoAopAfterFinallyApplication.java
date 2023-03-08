package de.dkh.springdemoaoppointcutdeclarations;

import de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO;
import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Applying of {@linkplain org.aspectj.lang.annotation.AfterReturning} advices.
 */
@SpringBootApplication
public class SpringdemoAopAfterFinallyApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        final AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        /**
         * Method call to provide the exception. We expect here {@linkplain de.dkh.springdemoaoppointcutdeclarations.aspect.after.AfterFinallyDemoAspect#afterFinallyFindAdvice(JoinPoint)} being called.
         * NOTE: the {@linkplain org.aspectj.lang.annotation.After} advice is called as finally block here, regardless the exception.
         */
        try {
            Account account = accountDAO.getFromAccountList();
        } catch (IndexOutOfBoundsException exp) {
            System.out.println("Method call accountDAO#getFromAccountList() exception being thrown!");
        }


        context.close();

    }

}
