package com.technoavi.fz.cb.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class FCControler {


    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/gt")
    @CircuitBreaker(name="nm", fallbackMethod = "callMSG_fallback")
    public String getMSGfromCS(){
        log.info("Activity received: getMSGfromCS" );
        String uri="http://localhost:8082/msg";
        ResponseEntity<String> res= restTemplate.getForEntity(uri, String.class);
        log.info("Activity received: getMSGfromCS" +uri +" and "+res.getBody());
       return "from Customer service -"+res.getBody();
    }


    @GetMapping("/ft")

    public String getMSGmCS(){


        return "from Fisherman service";
    }

    private  String callMSG_fallback(Throwable th){

        return "plz try again!!";
    }

}
