package com.narensoft.fs.controllers;

import com.narensoft.fs.DAO.FishermanRepository;
import com.narensoft.fs.models.Fish;
import com.narensoft.fs.models.Fisherman;
import com.narensoft.fs.service.FishermanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "fisherman")
public class FishermanController {

    @Autowired
    private FishermanRepository fishermanRepository;

    private FishermanService fishermanService;

    @Autowired
    FishermanController(FishermanService fishermanService) {
        this.fishermanService = fishermanService;
    }

    @PostMapping("/create")
    public ResponseEntity<Fisherman> createFisherman(@RequestBody Fisherman fisherman) {
        Fisherman fisherman1 = fishermanService.createFisherman(fisherman);
        return  new ResponseEntity<>(fisherman1, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Fisherman> getFishermanById(@PathVariable("id") int fishermanId) {
        Fisherman fisherman = fishermanService.getFishermanById(fishermanId);
        return new ResponseEntity<>(fisherman, HttpStatus.OK);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Fisherman>> getAllFisherman() {
        List<Fisherman> allFisherman = fishermanService.getAllFisherman();
        return new ResponseEntity<>(allFisherman, HttpStatus.OK);
    }

    @GetMapping("/get/loc")
    public ResponseEntity<List<Fisherman>> getFishermanByLocation(@RequestParam(value = "location") String location) {
        List<Fisherman> allFisherman = fishermanService.getFishermanByLocation(location);
        return new ResponseEntity<>(allFisherman, HttpStatus.OK);
    }

    @GetMapping("/get/loclist")
    public ResponseEntity<List<Fisherman>> getFishermanByLocationList(@RequestParam(value = "location") List<String> locations) {
        List<Fisherman> allFisherman = fishermanService.getFishermanByLocationList(locations);
        return new ResponseEntity<>(allFisherman, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Fisherman> updateFisherMan(@PathVariable("id") int id, @RequestBody Fisherman fisherman) {
        Fisherman fisherman1 = fishermanService.updateFisherMan(id, fisherman);
        return new ResponseEntity<>(fisherman1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFisherman(@PathVariable("id") int id) {
        fishermanService.deleteFisherman(id);
    }



    //For testing only
    @GetMapping("/add")
    public List<Fisherman> createFisherman() {
        Fisherman fisherman = new Fisherman();
        fisherman.setName("Fm 1")
                .setLocation("Asansol ");
        Fish f1, f2;
        f1 = new Fish();
        f2 = new Fish();
        f1.setName("fish 1");
        f2.setName("fish 2");
        fisherman.setFishList(Arrays.asList(f1, f2));
        fishermanRepository.save(fisherman);
        return fishermanRepository.findAll();
    }

    @GetMapping("/find")
    public List<Fisherman> findFisherman() {
        System.out.println("findFisherman test: ");
        return fishermanRepository.findTest(1);
    }
}
