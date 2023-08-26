package com.technoavi.fisheriz.cs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyControler {

    @GetMapping("/msg")
    public String m1(   ){
        return "new ResponseEntity<String> HttpStatus.OK)";

    }


}
