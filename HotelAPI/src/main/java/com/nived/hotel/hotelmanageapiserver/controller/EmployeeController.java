package com.nived.hotel.hotelmanageapiserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.nived.hotel.hotelmanageapiserver.entity.Employee;
import com.nived.hotel.hotelmanageapiserver.service.EmployeeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(
            @PathVariable Integer employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    // Add a new employee
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    // Update an existing employee
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(
            @PathVariable Integer employeeId,
            @RequestBody Employee employee) {
        return employeeService.updateEmployee(
                employeeId, employee);
    }

    // Delete employee
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(
            @PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}