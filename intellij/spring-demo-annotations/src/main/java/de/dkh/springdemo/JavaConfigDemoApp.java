package de.dkh.springdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * We test the JAVA spring configuration over {@linkplain SportConfig}.
 * Note the bean ID of {@linkplain TennisCoach} matched the {@linkplain org.springframework.context.annotation.Bean} annotated method in {@linkplain SportConfig}.
 */
public class JavaConfigDemoApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext javaApplicationContext = new AnnotationConfigApplicationContext(SportConfig.class);

        Coach myTennisCoach = javaApplicationContext.getBean("tennisCoachBean", Coach.class);

        System.out.println(myTennisCoach.getDailyWorkout());
        System.out.println(myTennisCoach.getDailyFortune());

        javaApplicationContext.close();
    }
}
