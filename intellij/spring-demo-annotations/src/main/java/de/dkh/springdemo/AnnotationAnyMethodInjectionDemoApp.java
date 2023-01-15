package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Here we test the any method injection of {@link FortuneService} in {@link SwimCoach}.
 * Further we test the properties setting over the {@code sport.properties}.
 */
public class AnnotationAnyMethodInjectionDemoApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        SwimCoach mySwimCoach = xmlApplicationContext.getBean("swimCoach", SwimCoach.class);

        System.out.println(mySwimCoach.getName());
        System.out.println(mySwimCoach.getEmail());
        System.out.println(mySwimCoach.getDailyWorkout());
        System.out.println(mySwimCoach.getDailyFortune());

        xmlApplicationContext.close();
    }
}
