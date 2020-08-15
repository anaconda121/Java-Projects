package com.votes.Votes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class VotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(VotesApplication.class, args);
	}

}
