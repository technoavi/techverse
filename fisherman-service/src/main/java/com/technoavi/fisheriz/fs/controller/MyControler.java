package com.technoavi.fisheriz.fs.controller;

import com.technoavi.fisheriz.fs.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class MyControler {
    @Autowired
    RestTemplate restTemplate;



    @Autowired
    WebClient.Builder builder;


    @GetMapping("/gt")
    public String getMSGfromCS(){


       String res= restTemplate.getForObject("http://numbersapi.com/54", String.class);

       return "from Customer service -"+res;
    }


    @GetMapping("/ft")

    public String getMSGmCS(){
        System.out.println("getMSGmCS invoked!! ");

        return "from Fisherman service";
    }

    //http://numbersapi.com/40
    @GetMapping("/num")
    public String getNum(){

        String res=  builder.build().get().
                uri("http://numbersapi.com/40")
                .retrieve()
                .bodyToMono(String.class)
                .block();


       return "response from other api - "+res;
    }
}
