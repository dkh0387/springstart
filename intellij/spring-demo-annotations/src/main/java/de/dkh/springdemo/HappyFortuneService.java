package de.dkh.springdemo;

import org.springframework.stereotype.Component;

@Component("myFortuneService")
public class HappyFortuneService implements FortuneService {

    public HappyFortuneService() {
    }

    @Override
    public String getFortune() {
        return "Today is your lucky day!";
    }

}
