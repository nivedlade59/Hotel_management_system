
package com.nived.hotel.hotelmanageapiserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.nived.hotel.hotelmanageapiserver.dto.RoomStatusRequest;
import com.nived.hotel.hotelmanageapiserver.entity.Room;
import com.nived.hotel.hotelmanageapiserver.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@CrossOrigin(origins = "http://localhost:5173")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Get all rooms
    @GetMapping
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    // Get only available rooms
    @GetMapping("/available")
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // Get a particular room by room number
    @GetMapping("/{roomNo}")
    public ResponseEntity<Room> getRoomByNumber(
            @PathVariable Integer roomNo) {

        return roomService.getRoomByNumber(roomNo)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PatchMapping("/{roomNo}/status")
    public ResponseEntity<Room> updateRoomStatus(
            @PathVariable Integer roomNo,
            @RequestBody RoomStatusRequest request) {

        try {
            Room.RoomStatus newStatus =
                    Room.RoomStatus.valueOf(request.getStatus());

            return roomService.updateRoomStatus(roomNo, newStatus)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    // Get available room numbers
    @GetMapping("/available/numbers")
    public List<Integer> getAvailableRoomNumbers() {
        return roomService.getAvailableRoomNumbers();
    }

    // Get occupied room numbers
    @GetMapping("/occupied/numbers")
    public List<Integer> getOccupiedRoomNumbers() {
        return roomService.getOccupiedRoomNumbers();
    }
}