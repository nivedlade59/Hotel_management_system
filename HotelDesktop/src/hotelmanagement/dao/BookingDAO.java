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
import hotelmanagement.model.Bill;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

public class BookingDAO {

    private final Gson gson = new Gson();

    // Allot a room to a customer
    public boolean allotRoom(int customerId, int roomNo) {

        try {
            JsonObject request = new JsonObject();
            request.addProperty("customerId", customerId);
            request.addProperty("roomNo", roomNo);

            String response = ApiClient.post(
                    "/api/bookings/allot",
                    request
            );

            return response != null && !response.isBlank();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            return false;
        }
    }
    
    // Get bill details for an occupied room
    public Bill getBillDetails(int roomNo) {

        try {
            String response = ApiClient.get(
                    "/api/bookings/bill/" + roomNo
            );

            if (response == null || response.isBlank()) {
                return null;
            }

            JsonObject json = gson.fromJson(
                    response,
                    JsonObject.class
            );

            Bill bill = new Bill();

            bill.setBookingId(
                    json.get("bookingId").getAsInt()
            );

            bill.setCustomerId(
                    json.get("customerId").getAsInt()
            );

            bill.setCustomerName(
                    json.get("customerName").getAsString()
            );

            bill.setPhone(
                    json.get("phone").getAsString()
            );

            bill.setAddress(
                    json.get("address").getAsString()
            );

            bill.setRoomNo(
                    json.get("roomNo").getAsInt()
            );

            bill.setPrice(
                    json.get("price").getAsDouble()
            );

            bill.setDays(
                    json.get("days").getAsInt()
            );

            bill.setTotal(
                    json.get("total").getAsDouble()
            );

            return bill;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            return null;
        }
    }

    // Check out a booking
    public boolean checkout(int bookingId) {

        try {
            String response = ApiClient.patch(
                    "/api/bookings/" + bookingId + "/checkout",
                    ""
            );

            return response != null && !response.isBlank();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            return false;
        }
    }
}