package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Here we test using scopes by annotation {@linkplain org.springframework.context.annotation.Scope}.
 * Further we test the application of {@link javax.annotation.PostConstruct} {@link javax.annotation.PreDestroy}.
 */
public class AnnotationBeanScopeDemoApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        SwimCoach mySwimCoach1 = xmlApplicationContext.getBean("swimCoach", SwimCoach.class);
        SwimCoach mySwimCoach2 = xmlApplicationContext.getBean("swimCoach", SwimCoach.class);

        System.out.println("Both instances are equals? " + mySwimCoach1.equals(mySwimCoach2));

        System.out.println(mySwimCoach1.getName());
        System.out.println(mySwimCoach1.getEmail());
        System.out.println(mySwimCoach1.getNickName());
        System.out.println(mySwimCoach1.getDailyWorkout());
        System.out.println(mySwimCoach1.getDailyFortune());

        xmlApplicationContext.close();

        System.out.println(mySwimCoach1.getNickName());
    }
}
