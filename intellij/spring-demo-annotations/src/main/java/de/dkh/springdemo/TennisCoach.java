package de.dkh.springdemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    /**
     * NOTE: Spring component scan will autowire an implementation of {@linkplain FortuneService}.
     * If there are many of them we need additional annotation {@link org.springframework.beans.factory.annotation.Qualifier}.
     * So any instance of {@link this} is created with an instance of {@linkplain FortuneService}.
     * <p>
     * As of Spring Framework 4.3, an @Autowired annotation on such a constructor is no longer necessary if the target bean only defines one constructor to begin with.
     * However, if several constructors are available, at least one must be annotated to teach the container which one to use.
     * <p>
     * Further we specify the concrete {@linkplain FortuneService} implementation to use, because we have more that one in project.
     * Pay attention to the bean ID: just the classname small written.
     *
     * @param fortuneService
     */
    @Autowired
    public TennisCoach(@Qualifier("happyFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Play Tennis for 30 min!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
