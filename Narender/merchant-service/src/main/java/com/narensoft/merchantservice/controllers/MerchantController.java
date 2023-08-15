package com.narensoft.merchantservice.controllers;

import com.narensoft.merchantservice.models.Merchant;
import com.narensoft.merchantservice.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MerchantController {
    @Autowired
    private MerchantService merchantService;

    @PostMapping("/create")
    public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
        return new ResponseEntity<>(merchantService.createMerchant(merchant), HttpStatus.OK);
    }

    @GetMapping("/get-all-merchant")
    public ResponseEntity<List<Merchant>> getAllMerchant() {
        return new ResponseEntity<>(merchantService.getAllMerchant(), HttpStatus.OK);
    }

    @GetMapping("/get-merchant/{merchantId}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable Integer merchantId) {
        return new ResponseEntity<>(merchantService.getMerchantById(merchantId), HttpStatus.OK);
    }

    @PutMapping("/update-merchant/{merchantId}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable Integer merchantId, @RequestBody Merchant merchant) {
        return new ResponseEntity<>(merchantService.updateMerchant(merchantId, merchant), HttpStatus.OK);
    }

    @DeleteMapping("/delete-merchant/{id}")
    public ResponseEntity<String> deleteMerchant(Integer merchantId) {
        return new ResponseEntity<>(merchantService.deleteMerchant(merchantId), HttpStatus.OK);
    }

}
