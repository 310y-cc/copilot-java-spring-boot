package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Spring Boot application.
 * This class bootstraps the application context and starts the embedded server.
 */
@SpringBootApplication
public class DemoApplication {

	/**
	 * Starts the application with the provided command-line arguments.
	 *
	 * @param args runtime arguments passed to the application at startup
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
