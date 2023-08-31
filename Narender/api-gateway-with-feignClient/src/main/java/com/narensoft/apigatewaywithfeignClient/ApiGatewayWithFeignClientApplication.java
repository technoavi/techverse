package com.narensoft.apigatewaywithfeignClient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ApiGatewayWithFeignClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayWithFeignClientApplication.class, args);
	}

}
