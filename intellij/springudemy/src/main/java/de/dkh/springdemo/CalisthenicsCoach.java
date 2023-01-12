package de.dkh.springdemo;

import lombok.Getter;

public class CalisthenicsCoach implements Coach {

    private FortuneService fortuneService;
    @Getter
    private String name;

    public CalisthenicsCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Do 20 pull ups!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    /**
     * Bean lifecycle here: we define {@code init()} and {@code destroy()} methods to wire them in the {@code beanLifeCycle-applicationContext.xml}.
     * If a bean instance is created those should be called and name defined.
     */
    public void init() {
        name = "Denis";
    }

    public void destroy() {
        this.name = null;
        System.out.println("Coach name reset!");
    }
}
