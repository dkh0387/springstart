package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * See bean life cycle in action.
 */
public class BeanLifeCycleDemoApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
        CalisthenicsCoach coach = xmlContext.getBean("myCoach", CalisthenicsCoach.class);
        /**
         * {@linkplain CalisthenicsCoach#init()} calling here...
         */
        System.out.println("Coach name? " + coach.getName());

        xmlContext.close();
        /**
         * {@linkplain CalisthenicsCoach#destroy()} calling here...
         */
    }

}
