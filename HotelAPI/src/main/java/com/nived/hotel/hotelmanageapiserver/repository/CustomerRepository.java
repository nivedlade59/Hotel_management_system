package com.nived.hotel.hotelmanageapiserver.repository;

import com.nived.hotel.hotelmanageapiserver.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
        extends JpaRepository<Customer, Integer> {
}