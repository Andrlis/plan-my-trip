package by.andrlis.planmytrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class PlanMyTripApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanMyTripApplication.class, args);
    }

}
