package fr.osb.smartf.agent.dispatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by mpaltanea on 22.07.2016.
 */

/*
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        */
/*"fr.osb.smartf.agent.dispatcher.main.config",
        "fr.osb.smartf.agent.dispatcher.jms",
        "fr.osb.smartf.agent.dispatcher.service",
        "fr.osb.smartf.agent.dispatcher.security",*//*

        "fr.osb.smartf.agent.dispatcher",
})
@Configuration
public class TestContextConfiguration {

}*/

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
    "fr.osb.smartf.agent.dispatcher",
})
public class TestContextConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(TestContextConfiguration.class, args);
    }

}
