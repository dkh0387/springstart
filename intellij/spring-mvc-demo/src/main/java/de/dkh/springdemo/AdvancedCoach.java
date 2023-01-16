package de.dkh.springdemo;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Here we apply ALL of the learned features:
 * 1. {@linkplain Autowired} using constructor
 * 2. Setting of properties using .properties file
 * 3. post construct and pre destroy methods
 * 4. Qualifier concept to specify an implementation of interfaces
 */
//@Component("advancedCoach")
public class AdvancedCoach implements Coach {

    private FortuneService fortuneService;
    @Getter
    @Value("${foo.email}")
    private String email;
    @Getter
    @Value("${foo.name}")
    private String name;
    @Getter
    private String nickName;

    @Autowired
    public AdvancedCoach(@Qualifier("randomFortuneService") FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Do your advanced WO!";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    /**
     * Method calling just after instantiation.
     */
    @PostConstruct
    private void initNickname() {
        nickName = "DKH8703";
    }

    /**
     * Method calling just before destroying.
     * NOTE: this one is called ONLY if {@linkplain Scope == singleton}.
     * Example to handle prototype scopes see {@code springudemy}.
     */
    @PreDestroy
    private void resetNickName() {
        nickName = null;
    }
}
