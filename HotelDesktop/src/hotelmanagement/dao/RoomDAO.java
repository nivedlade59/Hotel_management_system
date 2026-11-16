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
import hotelmanagement.model.Room;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomDAO {

    private final Gson gson = new Gson();

    public String getAvailableRooms() {

        StringBuilder rooms = new StringBuilder();

        try {
            String response =
                    ApiClient.get("/api/rooms/available");

            Type roomListType =
                    new TypeToken<List<Room>>() {}.getType();

            List<Room> roomList =
                    gson.fromJson(response, roomListType);

            if (roomList != null) {
                for (Room room : roomList) {
                    rooms.append("Room No : ")
                         .append(room.getRoomNo())
                         .append("     Price : ")
                         .append(room.getPrice())
                         .append("\n");
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return rooms.toString();
    }


    public List<Integer> getAvailableRoomNumbers() {

        List<Integer> roomList = new ArrayList<>();

        try {
            String response =
                    ApiClient.get("/api/rooms/available/numbers");

            Type numberListType =
                    new TypeToken<List<Integer>>() {}.getType();

            List<Integer> result =
                    gson.fromJson(response, numberListType);

            if (result != null) {
                roomList.addAll(result);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return roomList;
    }


    public boolean updateRoomStatus(int roomNo, String status) {

        Map<String, String> statusData = new HashMap<>();
        statusData.put("status", status);

        try {
            ApiClient.patch(
                    "/api/rooms/" + roomNo + "/status",
                    statusData
            );

            return true;

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }

            return false;
        }
    }


    public List<Integer> getOccupiedRoomNumbers() {

        List<Integer> roomList = new ArrayList<>();

        try {
            String response =
                    ApiClient.get("/api/rooms/occupied/numbers");

            Type numberListType =
                    new TypeToken<List<Integer>>() {}.getType();

            List<Integer> result =
                    gson.fromJson(response, numberListType);

            if (result != null) {
                roomList.addAll(result);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return roomList;
    }
}