package com.minijuegos.userfunctions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableDiscoveryClient
public class UserfunctionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserfunctionsApplication.class, args);
	}

}
