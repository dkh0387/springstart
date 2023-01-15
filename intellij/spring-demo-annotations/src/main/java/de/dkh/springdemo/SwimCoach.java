package de.dkh.springdemo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Example of {@linkplain Autowired} using any method.
 * Further we specify the concrete {@linkplain FortuneService} implementation to use, because we have more that one in project.
 * Pay attention to the bean ID: just the classname small written.
 * Further we use properties form {@code sport.properties} here.
 */
@Component
@NoArgsConstructor
public class SwimCoach implements Coach {

    private FortuneService fortuneService;
    @Getter
    @Value("${foo.email}")
    private String email;
    @Getter
    @Value("${foo.name}")
    private String name;

    @Override
    public String getDailyWorkout() {
        return "Swim every single day!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    @Autowired
    public void setFortuneServiceInstance(@Qualifier("randomFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }
}
