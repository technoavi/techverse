package com.technoavi.fisheriz.cs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI userMicroserviceOpenAPI(){
        return  new OpenAPI()
                .info(new Info().title("CATALOGUE-SERVICE")
                        .description("Microservice")
                        .version("2.0"));
    }



}
