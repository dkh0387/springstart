package de.dkh.springdemoaoppointcutdeclarations;

import de.dkh.springdemoaoppointcutdeclarations.aspect.DemoLoggingAspectWithJoinPoint;
import de.dkh.springdemoaoppointcutdeclarations.dao.AccountDAO;
import de.dkh.springdemoaoppointcutdeclarations.entity.Account;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringdemoAopPointcutDeclarationsApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        final AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);
        /**
         * 1. Call with {@linkplain org.aspectj.lang.annotation.Before} advise
         * on spec. class {@linkplain AccountDAO} with spec. method {@linkplain AccountDAO#addAccounts(List)}.
         * There is NO {@linkplain org.aspectj.lang.reflect.Advice} to execute!
         */
        accountDAO.addAccounts(List.of(new Account("Denis", "TOP")));

        /**
         * 2. Call with {@linkplain org.aspectj.lang.annotation.Before} advise
         * on spec. class {@linkplain AccountDAO} with spec. method {@linkplain AccountDAO#updateAccount(Account)}.
         * There is a {@linkplain org.aspectj.lang.reflect.Advice} to execute:
         * {@linkplain DemoLoggingAspectWithJoinPoint#beforeUpdateAdvice()}!
         */
        accountDAO.updateAccount(new Account("Elena", "TOP"));

    }

}
