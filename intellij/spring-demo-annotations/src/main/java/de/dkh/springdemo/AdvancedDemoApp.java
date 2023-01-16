package de.dkh.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Testing ALL features in once.
 */
public class AdvancedDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext javaApplicationContext = new AnnotationConfigApplicationContext(SportConfig.class);

        AdvancedCoach myAdvancedCoach = javaApplicationContext.getBean("advancedCoach", AdvancedCoach.class);

        System.out.println(myAdvancedCoach.getName());
        System.out.println(myAdvancedCoach.getEmail());
        System.out.println(myAdvancedCoach.getDailyWorkout());
        System.out.println(myAdvancedCoach.getDailyFortune());

        System.out.println("Nickname after creating: " + myAdvancedCoach.getNickName());

        javaApplicationContext.close();

        System.out.println("Nickname before destroying: " + myAdvancedCoach.getNickName());
    }
}
