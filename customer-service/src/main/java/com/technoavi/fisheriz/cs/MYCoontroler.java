package com.technoavi.fisheriz.cs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MYCoontroler {

    @GetMapping("/msg")
    public String m1(){
        return "Success!!";
    }
}
