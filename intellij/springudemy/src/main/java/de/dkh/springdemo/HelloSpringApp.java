package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Here we are using an xml Application context to get the defined implementation of {@linkplain Coach} from an xml config file.
 * It allows us to costomize the App for different sport types.
 * Note the usage of the (constructor) dependency injection here (see {@linkplain FortuneService}).
 * We can get the injected field values from {@linkplain CricketCoach}, which are setted directly in {@code applicationContext.xml}.
 */
public class HelloSpringApp {

    public static void main(String[] args) {
        /*
        here is where the Spring framework reads all beans and their dependencies from {@code applicationContext.xml}.
         */
        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Coach coach = xmlContext.getBean("myCoach", Coach.class);
        CricketCoach myCricketCoach = xmlContext.getBean("myCricketCoach", CricketCoach.class);

        System.out.println(coach.getDailyWorkout());
        System.out.println(coach.getDailyFortune());
        System.out.println("--------------Cricket coach infos here:---------------");
        System.out.println(myCricketCoach.getEmail());
        System.out.println(myCricketCoach.getTeam());
        System.out.println(myCricketCoach.getDailyWorkout());
        System.out.println(myCricketCoach.getDailyFortune());

        xmlContext.close();
    }
}
