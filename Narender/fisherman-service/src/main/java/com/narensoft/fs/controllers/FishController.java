package com.narensoft.fs.controllers;

import com.narensoft.fs.models.Fish;
import com.narensoft.fs.service.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/fish")
public class FishController {
    private FishService fishService;

    @Autowired
    FishController(FishService fishService){
        this.fishService = fishService;
    }



    @GetMapping("/get-all/{fishermanId}")
    public List<Fish> getAllFishForFisherman(@PathVariable("fishermanId") int fishermanId) {
        return fishService.getAllFishForFisherman(fishermanId);
    }

    @PostMapping("/add/{fishermanId}")
    public List<Fish> addFish(@PathVariable("fishermanId") int fishermanId, @RequestBody Fish fish) {
        return null;
    }

    @DeleteMapping("/remove/{fishermanId}")
    public void removeFish(@PathVariable("fishermanId") int fishermanId,@RequestParam(value = "fishId") int fishId) {

    }

    @GetMapping("/get-by-location")
    public List<Fish> getAllFishByLocation(@RequestParam(value = "location") String location) {
        return null;
    }

    @GetMapping("/get-by-location/list")
    public List<Fish> getAllFishByLocationList(@RequestParam(value = "location") List<String> locationList) {
        return null;
    }
}