/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.dao;

/**
 *
 * @author Nived
 */

import hotelmanagement.api.ApiClient;
import hotelmanagement.model.Employee;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private final Gson gson = new Gson();

    // Add a new employee
    public boolean addEmployee(Employee employee) {

        try {
            ApiClient.post("/api/employees", employee);
            return true;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            return false;
        }
    }

    // Get all employees
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        try {
            String response =
                    ApiClient.get("/api/employees");

            Type employeeListType =
                    new TypeToken<List<Employee>>() {}.getType();

            List<Employee> result =
                    gson.fromJson(response, employeeListType);

            if (result != null) {
                employeeList.addAll(result);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return employeeList;
    }
}