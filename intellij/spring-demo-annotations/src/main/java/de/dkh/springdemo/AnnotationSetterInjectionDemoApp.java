package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Here we test the setter injection of {@link FortuneService} in {@link FootballCoach}.
 */
public class AnnotationSetterInjectionDemoApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach myFootballCoach = xmlApplicationContext.getBean("footballCoach", Coach.class);

        System.out.println(myFootballCoach.getDailyWorkout());
        System.out.println(myFootballCoach.getDailyFortune());

        xmlApplicationContext.close();
    }
}

