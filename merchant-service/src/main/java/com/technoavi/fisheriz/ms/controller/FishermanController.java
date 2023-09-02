package com.technoavi.fisheriz.ms.controller;


import com.technoavi.fisheriz.ms.model.Fisherman;
import com.technoavi.fisheriz.ms.model.Fishes;
import com.technoavi.fisheriz.ms.repos.FishermanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/fm")
public class FishermanController {
    @Autowired
    FishermanRepository fishermanRepository;

    @GetMapping("/all")
    public List<Fisherman> getAll() {
        return fishermanRepository.findAll();
    }

    @GetMapping("/{name}")
    public List<Fisherman> getUser(@PathVariable("name") final String name) {
        return fishermanRepository.findByName(name);

    }

    @GetMapping("/id/{id}")
    public Optional<Fisherman> getId(@PathVariable("id") final Integer id) {
        return fishermanRepository.findById(id);
    }

//    @GetMapping("/update/{id}/{name}")
//    public Fisherman update(@PathVariable("id") final Integer id, @PathVariable("name") final String name) {
//        Optional<Fisherman> users = getId(id);
//        users.ifPresent(users1 -> {
//            users1.setName(name);
//        });
//
//        Fisherman result = users.orElse(null);
//        return fishermanRepository.save(result);
//    }

}