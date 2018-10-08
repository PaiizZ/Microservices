package com.example.EmployeeSearchService;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDiscoveryClient
@SpringBootApplication
public class EmployeeSearchServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeSearchServiceApplication.class, args);
	}
}
