package com.narensoft.customerservice.controllers;

import com.narensoft.customerservice.models.Customer;
import com.narensoft.customerservice.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/customer")
public class CustomerController {
    Logger logger = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        logger.info("Inside createCustomer method");
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        logger.info("Inside getAllCustomers method");
        return new ResponseEntity<>(customerService.getAllCustomer(), HttpStatus.OK);
    }

    @GetMapping("/get-customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer customerId) {
        logger.info("Inside getCustomerById method");
        return new ResponseEntity<>(customerService.getCustomerBtId(customerId), HttpStatus.OK);
    }

    @PutMapping("/update-customer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customer) {
        logger.info("Inside updateCustomer method");
        return new ResponseEntity<>(customerService.updateCustomer(customerId, customer), HttpStatus.OK);
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<String> deleteCustomer(Integer customerId) {
        logger.info("Inside deleteCustomer method");
        return new ResponseEntity<>(customerService.deleteCustomer(customerId), HttpStatus.OK);
    }

}
