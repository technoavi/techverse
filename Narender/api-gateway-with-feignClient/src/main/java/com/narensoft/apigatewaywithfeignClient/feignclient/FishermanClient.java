package com.narensoft.apigatewaywithfeignClient.feignclient;

import com.narensoft.apigatewaywithfeignClient.OV.Fisherman;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/*If eureka client is there in this app just write the service name in the @FeignClient annotation
 * not need to write the url, It will fetch the url from service registry Eureka Server*/
@FeignClient(name = "fisherman-service", url = "http://localhost:8082/fisherman")
public interface FishermanClient {

    @GetMapping("/msg")
    public String msg();



    @PostMapping("/create")
    public ResponseEntity<Fisherman> createFisherman(@RequestBody Fisherman fisherman);

    @GetMapping("/get/{id}")
    public ResponseEntity<Fisherman> getFishermanById(@PathVariable("id") int fishermanId);

    @GetMapping("/get/all")
    public ResponseEntity<List<Fisherman>> getAllFisherman();

    @GetMapping("/get/loc")
    public ResponseEntity<List<Fisherman>> getFishermanByLocation(@RequestParam(value = "location") String location);

    @GetMapping("/get/loclist")
    public ResponseEntity<List<Fisherman>> getFishermanByLocationList(@RequestParam(value = "location") String locationList);

    @PutMapping("/update/{id}")
    public ResponseEntity<Fisherman> updateFisherMan(@PathVariable("id") int id, @RequestBody Fisherman fisherman);

    @DeleteMapping("/delete/{id}")
    public void deleteFisherman(@PathVariable("id") int id);


    //For testing only
    @GetMapping("/add")
    public List<Fisherman> createFisherman();

    @GetMapping("/find")
    public List<Fisherman> findFisherman();
}
