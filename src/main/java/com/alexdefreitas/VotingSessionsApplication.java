package com.alexdefreitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.alexdefreitas")
public class VotingSessionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotingSessionsApplication.class, args);
	}

}
