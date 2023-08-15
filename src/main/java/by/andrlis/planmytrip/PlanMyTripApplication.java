package by.andrlis.planmytrip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication()
public class PlanMyTripApplication {

    private final static Logger LOGGER = LoggerFactory.getLogger(PlanMyTripApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PlanMyTripApplication.class, args);
        LOGGER.info("Application started");
    }
}
