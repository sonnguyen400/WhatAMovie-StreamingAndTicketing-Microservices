package com.sonnguyen.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SnDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SnDiscoveryApplication.class, args);
	}

}
