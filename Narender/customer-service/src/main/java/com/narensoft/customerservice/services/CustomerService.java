package com.narensoft.customerservice.services;

import com.narensoft.customerservice.models.Customer;
import com.narensoft.customerservice.DAO.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    /**
    1. Is there any other way to check nested nulls?
     **/
    public Customer createCustomer(Customer customer) {
        if(Optional.ofNullable(customer).isPresent() &&
                Optional.ofNullable(customer.getName()).isPresent() &&
                Optional.ofNullable(customer.getPhone()).isPresent() &&
                Optional.ofNullable(customer.getLocation()).isPresent()) {
            return customerRepository.save(customer);
        }
        return new Customer();
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    /**
     1.How can we use findById(id).orElse()? How can we remove our own if condition?
     */
    public Customer getCustomerBtId(Integer id){
        if(customerRepository.findById(id).isPresent()){
            return customerRepository.findById(id).get();
        }
        return new Customer();
    }

    /**
     1. How to update customer name or address or phone?
     */
    public Customer updateCustomer(Integer id, Customer updatedCustomer){
        if(Optional.ofNullable(updatedCustomer).isPresent() && customerRepository.findById(id).isPresent()) {
                Customer customer = customerRepository.findById(id).get();
                customer.setName(updatedCustomer.getName());
                customer.setPhone(updatedCustomer.getPhone());
                customer.setLocation(updatedCustomer.getLocation());
                customer.setAddress(updatedCustomer.getAddress());
                customerRepository.save(customer);
        }
        return new Customer();
    }

    //to be implemented
    public String deleteCustomer(Integer id){
        if(customerRepository.findById(id).isPresent()){
            customerRepository.deleteById(id);
            return "Customer deleted";
        }
        return "Customer doesn't exist";
    }
}
