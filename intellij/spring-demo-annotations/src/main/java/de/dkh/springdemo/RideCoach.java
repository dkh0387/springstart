package de.dkh.springdemo;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Example of {@linkplain Autowired} WITHOUT using any method.
 * Behind the scene Java Reflection is being used.
 * Spring Team recommends: "Always use constructor based dependency injection in your beans. Always use assertions for mandatory dependencies".
 * <p>
 * Further we specify the concrete {@linkplain FortuneService} implementation to use, because we have more that one in project.
 * Pay attention to the bean ID: just the classname small written.
 */
@Component
@NoArgsConstructor
public class RideCoach implements Coach {

    @Autowired
    @Qualifier("happyFortuneService")
    private FortuneService fortuneService;

    @Override
    public String getDailyWorkout() {
        return "Swim every single day!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
