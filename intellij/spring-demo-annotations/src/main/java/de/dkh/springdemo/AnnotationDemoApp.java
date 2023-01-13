package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach myTennisCoach = xmlApplicationContext.getBean("tennisCoach", Coach.class);

        System.out.println(myTennisCoach.getDailyWorkout());
        System.out.println(myTennisCoach.getDailyFortune());

        xmlApplicationContext.close();
    }
}
