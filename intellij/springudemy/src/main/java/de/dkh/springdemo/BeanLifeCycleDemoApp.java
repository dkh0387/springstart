package de.dkh.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * See bean life cycle in action.
 */
public class BeanLifeCycleDemoApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext xmlContext = new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext.xml");
        boolean singleton = xmlContext.isSingleton("myCoachSingleton");
        CalisthenicsCoach coachSingleton = xmlContext.getBean("myCoachSingleton", CalisthenicsCoach.class);

        boolean prototype = xmlContext.isSingleton("myCoach");
        CalisthenicsCoach coach1 = xmlContext.getBean("myCoach", CalisthenicsCoach.class);
        CalisthenicsCoach coach2 = xmlContext.getBean("myCoach", CalisthenicsCoach.class);
        CalisthenicsCoach coach3 = xmlContext.getBean("myCoach", CalisthenicsCoach.class);

        /**
         * {@linkplain CalisthenicsCoach#init()} calling here...
         */
        System.out.println("coachSingleton name? " + coachSingleton.getName());
        System.out.println("coachSingleton instance is singleton? " + singleton);

        xmlContext.close();
        /**
         * {@linkplain CalisthenicsCoach#destroy()} calling here,
         * but only if {@code singleton == true} the method is calling directly from the .xml file,
         * otherwise {@linkplain MyCustomBeanProcessor#destroy()} is calling here...
         */
    }

}
