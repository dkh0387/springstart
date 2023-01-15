package de.dkh.springdemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Here we read fortune messages from {@code fortune.properties} into an array and get the random one.
 */
@Component
public class RandomFortuneService implements FortuneService {

    @Value("${fortune.messages}")
    private String fortunesFromFile;
    private String[] fortunes;

    public RandomFortuneService() {
    }

    @Override
    public String getFortune() {
        fortunes = fortunesFromFile.split(",");
        return fortunes[(int) (Math.random() * fortunes.length)].trim();
    }

}
