package com.websystique.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.pollsapi.springboot"})// same as @Configuration @EnableAutoConfiguration @ComponentScan combined
public class PollsRestApiApp {

	public static void main(String[] args) {
		SpringApplication.run(PollsRestApiApp.class, args);
	}
}
