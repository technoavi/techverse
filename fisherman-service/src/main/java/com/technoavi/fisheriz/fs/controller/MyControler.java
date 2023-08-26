package com.technoavi.fisheriz.fs.controller;

import com.technoavi.fisheriz.fs.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MyControler {
    @Autowired
    RestTemplate restTemplate;


//    @GetMapping("/gt")
//    public String getMSGfromCS(){
//
//        String uri="msg";
//       String res= restTemplate.getForObject("http://localhost:8082/msg", String.class);
//
//       return "from Customer service -"+res;
//    }


    @GetMapping("/ft")

    public String getMSGmCS(){


        return "from Fisherman service";
    }

}
