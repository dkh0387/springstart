package de.dkh.springdemoaoppointcutdeclarations.service;

import org.springframework.stereotype.Service;

@Service
public class TrafficFortuneService {

    /**
     * we throw an exception here, because we want to use {@linkplain org.aspectj.lang.annotation.Around} advice on this method
     * for handling it.
     *
     * @return
     */
    public String getFortune(boolean tripWire) {

        if (tripWire) {
            throw new RuntimeException("Exception is throwing!");
        }
        return "Today is your lucky day!";
    }
}
