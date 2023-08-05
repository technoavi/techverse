package com.narensoft.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FishermanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishermanServiceApplication.class, args);
	}

}
