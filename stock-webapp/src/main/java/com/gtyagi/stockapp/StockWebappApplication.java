package com.gtyagi.stockapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class StockWebappApplication {

	public static void main(String[] args) {
		SpringApplication.run(StockWebappApplication.class, args);
	}
}
