package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * See different bean scopes in action.
 */
public class BeanScopeDemoApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");
        Coach coach1 = xmlContext.getBean("myCoach", Coach.class);
        Coach coach2 = xmlContext.getBean("myCoach", Coach.class);

        System.out.println("Coach instances are equals? " + coach1.equals(coach2));
        System.out.println("\nMemory location for the 1. instance? " + coach1);
        System.out.println("\nMemory location for the 2. instance? " + coach2);

        xmlContext.close();
    }

}
