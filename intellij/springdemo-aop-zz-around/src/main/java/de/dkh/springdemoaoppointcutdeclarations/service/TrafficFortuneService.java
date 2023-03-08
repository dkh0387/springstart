package de.dkh.springdemoaoppointcutdeclarations.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneService {

    /**
     * we simulate a delay here, because we want to use {@linkplain org.aspectj.lang.annotation.Around} advice on this method
     * for measure the execution time.
     *
     * @return
     */
    public String getFortune() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return "Today is your lucky day!";
    }
}
