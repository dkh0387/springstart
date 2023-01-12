package de.dkh.springdemo;

public class BaseballCoach implements Coach {

    private FortuneService fortuneService;

    public BaseballCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run 30 min every day!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
