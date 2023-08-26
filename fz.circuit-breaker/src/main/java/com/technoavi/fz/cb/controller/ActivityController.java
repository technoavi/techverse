package com.technoavi.fz.cb.controller;


import com.technoavi.fz.cb.config.Activity;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequestMapping("/activity")
@RestController
public class ActivityController {

    private RestTemplate restTemplate;

    private final String BORED_API = "http://localhost:8082/msg";

    public ActivityController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    @CircuitBreaker(name = "randomActivity", fallbackMethod = "fallbackRandomActivity")
    public String getRandomActivity() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BORED_API, String.class);
        String activity = responseEntity.getBody();
        log.info("Activity received: " + activity);
        return activity;
    }

    public String fallbackRandomActivity(Throwable throwable) {
        return "Watch a video from TechPrimers";
    }

}