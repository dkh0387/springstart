package de.dkh.springdemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

public class CricketCoach implements Coach {
    /**
     * Here we use the setter dependency injection by Spring.
     * The setter will be called by framework automatically over the injection the field in {@code applicationContext.xml}.
     */
    @Setter
    private FortuneService fortuneService;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String team;

    @Override
    public String getDailyWorkout() {
        return "play cricket for 20 min every sunday!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune() + " " + "Go for a good cricket game!";
    }
}
