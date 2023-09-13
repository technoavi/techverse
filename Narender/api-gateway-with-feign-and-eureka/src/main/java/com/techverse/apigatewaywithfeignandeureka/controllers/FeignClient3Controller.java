package com.techverse.apigatewaywithfeignandeureka.controllers;

import com.techverse.apigatewaywithfeignandeureka.feignclients.FishermanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/feign3")
public class FeignClient3Controller {

    @Autowired
    private FishermanClient fishermanClient;

    @GetMapping("/get")
    public String msg(){
        return fishermanClient.msg();
    }
}
