package de.dkh.springdemo;

import org.springframework.stereotype.Component;

@Component
public class SadFortuneService implements FortuneService {

    public SadFortuneService() {
    }

    @Override
    public String getFortune() {
        return "Get rest to yourself if tired!";
    }

}
