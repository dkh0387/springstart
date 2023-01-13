package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Here we test the setter injection of {@link FortuneService} in {@link FootballCoach}.
 */
public class AnnotationSetterInjectionDemoApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach myfootballCoach = xmlApplicationContext.getBean("footballCoach", Coach.class);

        System.out.println(myfootballCoach.getDailyWorkout());
        System.out.println(myfootballCoach.getDailyFortune());

        xmlApplicationContext.close();
    }
}
