package com.mendel.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.mendel.challenge.controller", "com.mendel.challenge.service", "com.mendel.challenge.persistence"})
public class MendelChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MendelChallengeApplication.class, args);
	}

}
