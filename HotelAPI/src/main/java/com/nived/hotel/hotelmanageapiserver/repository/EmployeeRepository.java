package com.nived.hotel.hotelmanageapiserver.repository;

import com.nived.hotel.hotelmanageapiserver.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Integer> {
}