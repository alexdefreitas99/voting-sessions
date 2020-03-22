package com.alexdefreitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VotingSessionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingSessionsApplication.class, args);
    }

}
