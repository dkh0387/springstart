package de.dkh.springdemo;

public class HappyFortuneService implements FortuneService {

    public HappyFortuneService() {
    }

    @Override
    public String getFortune() {
        return "Today is your lucky day!";
    }

}
