package com.technoavi.fz.cb.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class FCControler {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/gt")
    @CircuitBreaker(name="myCircuitBreaker", fallbackMethod = "callMSG_fallback")
    @Retry(name = "myRetry")
    public String getMSGfromCS(){
        log.info("Activity received: getMSGfromCS" );
        String uri="http://localhost:8082/msg";
        String res= restTemplate.getForObject(uri, String.class);
        log.info("Activity received: getMSGfromCS" +uri +" and "+res);
       return "from Customer service -"+res;
    }


    @GetMapping("/ft")

    public String getMSGmCS(){


        return "from Fisherman service";
    }

    private  String callMSG_fallback(Exception ex){

        return "plz try again!!";
    }

}
