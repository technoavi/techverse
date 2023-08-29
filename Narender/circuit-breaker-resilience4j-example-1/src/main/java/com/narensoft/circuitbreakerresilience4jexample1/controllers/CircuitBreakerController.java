package com.narensoft.circuitbreakerresilience4jexample1.controllers;

import com.narensoft.circuitbreakerresilience4jexample1.OV.Fisherman;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@Slf4j //from lombok
@RestController
@RequestMapping(path = "/cb")
public class CircuitBreakerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/msg")
    public String msg() {
        log.info("msg invoked: Circuit Breaker");
        return "Hello from Circuit Breaker";
    }

    /*
    * @CircuitBreaker: annotation is used over the method that can fail(generally which make HTTP calls to other service).
    * name = "circuitBreakerName": , name attribute must be supplied to a circuit breaker and with this name only we need
    to configure the behaviour in properties(yml file).
    * fallbackMethod : which method to call if any kind of exception occur in this method.
     */

    @GetMapping("/get/{id}")
    @CircuitBreaker(name = "fishermanCircuitBreaker", fallbackMethod = "fisherman_fallback")
    public ResponseEntity<Fisherman> getFishermanById(@PathVariable("id") int fishermanId) {
        Fisherman fisherman = restTemplate.getForObject("http://localhost:8082/fisherman/get" +fishermanId, Fisherman.class);
        return new ResponseEntity<>(fisherman, HttpStatus.OK);
    }


    /* fisherman_fallback(Exception ex): This is a fallback method.
    * Whenever the circuit goes to "OPEN" state this method will be called.
    * The fist parameter must be Exception type.
    * The return type of fallback method must match with the original method.
    */
    public ResponseEntity<Fisherman> fisherman_fallback(Exception ex){
        log.info("fishmanService is down! Calling fisherman_fallback method");
        Fisherman fm = new Fisherman();
        return new ResponseEntity<>(fm, HttpStatus.OK);
    }
}
