package fr.osb.smartf.agent.worker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mpaltanea on 28.07.2016.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "fr.osb.smartf.agent.worker",
})
public class TestContextConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(TestContextConfiguration.class, args);
    }

}
