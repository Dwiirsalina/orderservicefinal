package com.pbkk.orderservice;



import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.pbkk.orderservice.deals.Deals;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan("com.pbkk.orderservice")
public class OrderServiceApplication {
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceApplication.class);

	public static void main(String[] args ) {
		
		SpringApplication.run(OrderServiceApplication.class, args);
	}
	
	
}
