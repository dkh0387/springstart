package de.dkh.springdemo;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * By annotating the class as {@code Component} we allow Spring auto scan and find this bean. Just like using .xml file,
 * but no bean item needed, see {@code applicationContext.xml}.
 * By default the ID ist just {@code <classname>}, so {@code tennisCoach} here.
 * We can get a bean instance in the main app class just as before (see {@linkplain AnnotationDemoApp}).
 */
@Component
@Scope("singleton")
public class TennisCoach implements Coach {

    private FortuneService fortuneService;

    public TennisCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Play Tennis for 30 min!";
    }

    @Override
    public String getDailyFortune() {
        return null;
    }
}
