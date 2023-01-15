package de.dkh.springdemo;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Example of {@linkplain Autowired} using setter.
 * <p>
 * Further we specify the concrete {@linkplain FortuneService} implementation to use, because we have more that one in project.
 * Pay attention to the bean ID: just the classname small written.
 */
@Component
@NoArgsConstructor
public class FootballCoach implements Coach {

    private FortuneService fortuneService;

    @Override
    public String getDailyWorkout() {
        return "Play football every single day!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    @Autowired
    public void setFortuneService(@Qualifier("happyFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
}
