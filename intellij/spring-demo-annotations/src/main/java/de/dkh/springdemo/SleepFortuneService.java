package de.dkh.springdemo;

import org.springframework.stereotype.Component;

@Component
public class SleepFortuneService implements FortuneService {

    public SleepFortuneService() {
    }

    @Override
    public String getFortune() {
        return "Sleep at least 8 hours per day and be happy!";
    }

}
