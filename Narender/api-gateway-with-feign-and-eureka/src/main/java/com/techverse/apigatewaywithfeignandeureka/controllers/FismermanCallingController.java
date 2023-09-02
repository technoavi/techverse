package com.techverse.apigatewaywithfeignandeureka.controllers;

import com.techverse.apigatewaywithfeignandeureka.OV.Fisherman;
import com.techverse.apigatewaywithfeignandeureka.feignclient.FishermanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/feign")
public class FismermanCallingController {

    @Autowired
    private FishermanClient fishermanClient;

    @GetMapping("/msg")
    public String msg(){
        String msg = fishermanClient.msg();
        return msg;
    }
}
