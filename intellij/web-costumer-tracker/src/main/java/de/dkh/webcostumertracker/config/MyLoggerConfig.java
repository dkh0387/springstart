package de.dkh.webcostumertracker.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * In Spring 5.1+ the logging level has been changed internally.
 * So in order to control the logging level we need the configuration for it.
 * NOTE: we do not annotate this class as {@linkplain org.springframework.stereotype.Component} since we explicitly list it in {@linkplain AppConfig}.
 */
public class MyLoggerConfig {

    @Value("${root.logger.level}")
    private String rootLoggerLevel;
    @Value("${printed.logger.level}")
    private String printedLoggerLevel;

    public MyLoggerConfig(String rootLoggerLevel, String printedLoggerLevel) {
        this.rootLoggerLevel = rootLoggerLevel;
        this.printedLoggerLevel = printedLoggerLevel;
    }

    @PostConstruct
    public void initLogger() {

        // parse levels
        Level rootLevel = Level.parse(rootLoggerLevel);
        Level printedLevel = Level.parse(printedLoggerLevel);

        // get logger for app context
        Logger applicationContextLogger = Logger.getLogger(AnnotationConfigApplicationContext.class.getName());

        // get parent logger
        Logger loggerParent = applicationContextLogger.getParent();

        // set root logging level
        loggerParent.setLevel(rootLevel);

        // set up console handler
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(printedLevel);
        consoleHandler.setFormatter(new SimpleFormatter());

        // add handler to the logger
        loggerParent.addHandler(consoleHandler);
    }
}
