package com.narensoft.apigatewaywithfeignClient.controllers;


import com.narensoft.apigatewaywithfeignClient.OV.Fisherman;
import com.narensoft.apigatewaywithfeignClient.feignclient.FishermanClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/feign-client")
public class FismermanCallingController {

    @Autowired
    private FishermanClient fishermanClient;

    @GetMapping("/msg")
    public String msg(){
        String msg = fishermanClient.msg();
        //callGetAllFisherman();
        return msg;
    }

    public ResponseEntity<List<Fisherman>> callGetAllFisherman() {
        return fishermanClient.getAllFisherman();
    }
}
