package com.narensoft.merchantservice.controllers;

import com.narensoft.merchantservice.OV.Customer;
import com.narensoft.merchantservice.models.Merchant;
import com.narensoft.merchantservice.services.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MerchantController {
    Logger logger = LoggerFactory.getLogger(MerchantController.class);
    @Autowired
    private MerchantService merchantService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        logger.info("createMerchant method called");
        return new ResponseEntity<>(merchantService.createMerchant(merchant), HttpStatus.OK);
    }

    @GetMapping("/get-all-merchant")
    public ResponseEntity<List<Merchant>> getAllMerchant() {
        logger.info("getAllMerchant method called");
        return new ResponseEntity<>(merchantService.getAllMerchant(), HttpStatus.OK);
    }

    @GetMapping("/get-merchant/{merchantId}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable("merchantId") Integer merchantId) {
        logger.info("getMerchantById method called");
        return new ResponseEntity<>(merchantService.getMerchantById(merchantId), HttpStatus.OK);
    }

    @PutMapping("/update-merchant/{merchantId}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable("merchantId") Integer merchantId, @RequestBody Merchant merchant) {
        logger.info("updateMerchant method called");
        return new ResponseEntity<>(merchantService.updateMerchant(merchantId, merchant), HttpStatus.OK);
    }

    @DeleteMapping("/delete-merchant/{id}")
    public ResponseEntity<String> deleteMerchant(@PathVariable("id") Integer merchantId) {
        logger.info("deleteMerchant method called");
        return new ResponseEntity<>(merchantService.deleteMerchant(merchantId), HttpStatus.OK);
    }

    @GetMapping("/cs-from-ms/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") int id) {
        logger.info("getCustomerById method called from MerchantController");
        HttpEntity<Customer> customerHttpEntityEntity = new HttpEntity<>(new Customer());
        Customer customer = restTemplate.getForObject("http://localhost:8081/customer/get-customer/1", Customer.class);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

}
