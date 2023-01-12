package de.dkh.springdemo;

public class RandomFortuneService implements FortuneService {
    String[] fortunes = {"Good luck!", "have a great day!", "You will do it!"};

    @Override
    public String getFortune() {
        return fortunes[(int) (Math.random() * fortunes.length)];
    }
}
