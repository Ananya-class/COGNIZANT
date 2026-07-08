package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class); // [cite: 74]

    public static void main(String[] args) {
        LOGGER.info("START"); // [cite: 77]
        SpringApplication.run(SpringLearnApplication.class, args);
        displayDate();
        LOGGER.info("END"); // [cite: 81]
    }

    public static void displayDate() {
        LOGGER.info("START: displayDate");
        LOGGER.debug("Current Context operational date loaded successfully."); // [cite: 79]
        LOGGER.info("END: displayDate");
    }
}
