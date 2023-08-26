package com.technoavi.fisheriz.fs.config;

import org.springframework.boot.web.client.ClientHttpRequestFactorySupplier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

//    @Bean
//    public RestTemplate getRestTemplate(){
//
//       return new RestTemplate();
//  }
    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder

                .setConnectTimeout(Duration.ofMinutes(15))
                .setReadTimeout(Duration.ofMinutes(15))
                .build();
    }
}



