package com.nived.hotel.hotelmanageapiserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.nived.hotel.hotelmanageapiserver.entity.Customer;
import com.nived.hotel.hotelmanageapiserver.service.CustomerService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:5173")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Get all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get customer by ID
    @GetMapping("/{customerId}")
    public Customer getCustomerById(
            @PathVariable Integer customerId) {
        return customerService.getCustomerById(customerId);
    }

    // Add a new customer
    @PostMapping
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    // Update an existing customer
    @PutMapping("/{customerId}")
    public Customer updateCustomer(
            @PathVariable Integer customerId,
            @RequestBody Customer customer) {
        return customerService.updateCustomer(
                customerId, customer);
    }

    // Delete customer
    @DeleteMapping("/{customerId}")
    public void deleteCustomer(
            @PathVariable Integer customerId) {
        customerService.deleteCustomer(customerId);
    }
}