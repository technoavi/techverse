package com.techverse.apigatewaywithfeignandeureka.feignclient;


import com.techverse.apigatewaywithfeignandeureka.OV.Fisherman;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*If eureka client is there in this app just write the service name in the @FeignClient annotation
 * not need to write the url, It will fetch the url from service registry Eureka Server*/
@FeignClient("FISHERMAN-SERVICE")
public interface FishermanClient {

    @GetMapping("fm/msg")
    public String msg();



    @PostMapping("fm/create")
    public ResponseEntity<Fisherman> createFisherman(@RequestBody Fisherman fisherman);

    @GetMapping("fm/get/{id}")
    public ResponseEntity<Fisherman> getFishermanById(@PathVariable("id") int fishermanId);

    @GetMapping("fm/get/all")
    public ResponseEntity<List<Fisherman>> getAllFisherman();

    @GetMapping("fm/get/loc")
    public ResponseEntity<List<Fisherman>> getFishermanByLocation(@RequestParam(value = "location") String location);

    @GetMapping("fm/get/loclist")
    public ResponseEntity<List<Fisherman>> getFishermanByLocationList(@RequestParam(value = "location") String locationList);

    @PutMapping("fm/update/{id}")
    public ResponseEntity<Fisherman> updateFisherMan(@PathVariable("id") int id, @RequestBody Fisherman fisherman);

    @DeleteMapping("fm/delete/{id}")
    public void deleteFisherman(@PathVariable("id") int id);


    //For testing only
    @GetMapping("fm/add")
    public List<Fisherman> createFisherman();

    @GetMapping("fm/find")
    public List<Fisherman> findFisherman();
}
