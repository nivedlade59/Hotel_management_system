package com.nived.hotel.hotelmanageapiserver.service;

import com.nived.hotel.hotelmanageapiserver.entity.Employee;
import com.nived.hotel.hotelmanageapiserver.repository.EmployeeRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Get employee by ID
    public Employee getEmployeeById(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Employee not found: " + employeeId));
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        // Let the database generate the employee ID
        employee.setEmployeeId(null);

        return employeeRepository.save(employee);
    }

    // Update an existing employee
    public Employee updateEmployee(
            Integer employeeId, Employee updatedEmployee) {

        Employee existingEmployee = getEmployeeById(employeeId);

        existingEmployee.setEmployeeName(
                updatedEmployee.getEmployeeName());

        existingEmployee.setPhone(
                updatedEmployee.getPhone());

        existingEmployee.setDesignation(
                updatedEmployee.getDesignation());

        return employeeRepository.save(existingEmployee);
    }

    // Delete employee
    public void deleteEmployee(Integer employeeId) {
        Employee employee = getEmployeeById(employeeId);
        employeeRepository.delete(employee);
    }
}