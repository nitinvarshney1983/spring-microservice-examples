package com.techwhisky.spring.cloud.discovery.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TechwhiskyDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechwhiskyDiscoveryServerApplication.class, args);
	}

}
