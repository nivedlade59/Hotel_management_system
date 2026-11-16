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
import hotelmanagement.model.Customer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final Gson gson = new Gson();

    // Add a new customer
    public boolean addCustomer(Customer customer) {

        try {
            ApiClient.post("/api/customers", customer);
            return true;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            return false;
        }
    }

    // Get customer by ID
    public Customer getCustomer(int customerId) {

        try {
            String response = ApiClient.get(
                    "/api/customers/" + customerId);

            return gson.fromJson(response, Customer.class);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            return null;
        }
    }

    // Get all customers
    public List<Customer> getAllCustomers() {

        List<Customer> customerList = new ArrayList<>();

        try {
            String response = ApiClient.get("/api/customers");

            Type customerListType =
                    new TypeToken<List<Customer>>() {}.getType();

            List<Customer> result =
                    gson.fromJson(response, customerListType);

            if (result != null) {
                customerList.addAll(result);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return customerList;
    }
}