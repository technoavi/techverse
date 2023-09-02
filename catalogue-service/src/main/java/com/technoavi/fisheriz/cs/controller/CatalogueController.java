package com.technoavi.fisheriz.cs.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class CatalogueController {

private String product;

    @PostMapping("/add/{name}/{lname}")
    public String getMSGfromCS(@PathVariable String name,@PathVariable String lname){
        product=name+lname;
        return "Your product  is -"+product;

    }


    @GetMapping("/get-name")

    public String getName(){


        return product;
    }


    @PutMapping("/update")
    public String update(@PathVariable String name){

        product=name;

       return "updated product is - "+product;
    }
    @DeleteMapping("/remove")
    public String remove(@PathVariable String name){

        product="";

        return "removed  product !! ";
    }
}
