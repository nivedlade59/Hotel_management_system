package com.nived.hotel.hotelmanageapiserver.service;

import com.nived.hotel.hotelmanageapiserver.entity.Customer;
import com.nived.hotel.hotelmanageapiserver.repository.CustomerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Customer getCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found: " + customerId));
    }

    // Add a new customer
    public Customer addCustomer(Customer customer) {
        customer.setCustomerId(null);
        return customerRepository.save(customer);
    }

    // Update an existing customer
    public Customer updateCustomer(
            Integer customerId, Customer updatedCustomer) {

        Customer existingCustomer = getCustomerById(customerId);

        existingCustomer.setCustomerName(
                updatedCustomer.getCustomerName());
        existingCustomer.setPhone(
                updatedCustomer.getPhone());
        existingCustomer.setIdProof(
                updatedCustomer.getIdProof());
        existingCustomer.setAddress(
                updatedCustomer.getAddress());
        existingCustomer.setDays(
                updatedCustomer.getDays());

        return customerRepository.save(existingCustomer);
    }

    // Delete customer
    public void deleteCustomer(Integer customerId) {
        Customer customer = getCustomerById(customerId);
        customerRepository.delete(customer);
    }
}