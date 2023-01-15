package de.dkh.springdemo;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Example of {@linkplain Autowired} using any method.
 * Further we specify the concrete {@linkplain FortuneService} implementation to use, because we have more that one in project.
 * Pay attention to the bean ID: just the classname small written.
 * However: if the classname has more than one capital letter like here the ID is EXACTLY the classname!
 */
@Component
@NoArgsConstructor
public class DANCECoach implements Coach {

    private FortuneService fortuneService;

    @Override
    public String getDailyWorkout() {
        return "Swim every single day!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    @Autowired
    public void setFortuneServiceInstance(@Qualifier("sleepFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
}
