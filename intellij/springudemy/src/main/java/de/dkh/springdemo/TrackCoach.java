package de.dkh.springdemo;

public class TrackCoach implements Coach {

    private FortuneService fortuneService;

    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    public TrackCoach() {

    }

    @Override
    public String getDailyWorkout() {
        return "Run 5k!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
