package com.techverse.apigatewaywithfeignandeureka.feignclients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "FISHERMAN-SERVICE")
public interface FishermanClient {
    @GetMapping("fm/msg")
    String msg();
}
