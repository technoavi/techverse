package com.narensoft.fisherizconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class FisherizConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FisherizConfigServerApplication.class, args);
	}

}
