package com.technoavi.fisheriz.fs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FishermanServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FishermanServiceApplication.class, args);
	}

}
