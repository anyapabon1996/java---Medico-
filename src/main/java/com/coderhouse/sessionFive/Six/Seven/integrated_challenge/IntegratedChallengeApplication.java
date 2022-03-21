package com.coderhouse.sessionFive.Six.Seven.integrated_challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class IntegratedChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegratedChallengeApplication.class, args);
	}

}
