
package com.nived.hotel.hotelmanageapiserver.service;

import com.nived.hotel.hotelmanageapiserver.entity.Room;
import com.nived.hotel.hotelmanageapiserver.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get one room by room number
    public Optional<Room> getRoomByNumber(Integer roomNo) {
        return roomRepository.findById(roomNo);
    }

    // Get only available rooms
    public List<Room> getAvailableRooms() {
        return roomRepository.findByStatusOrderByRoomNoAsc(
                Room.RoomStatus.Available
        );
    }

    // Get only available room numbers
    public List<Integer> getAvailableRoomNumbers() {
        return roomRepository.findByStatusOrderByRoomNoAsc(
                        Room.RoomStatus.Available
                )
                .stream()
                .map(Room::getRoomNo)
                .collect(Collectors.toList());
    }

    // Get only occupied room numbers
    public List<Integer> getOccupiedRoomNumbers() {
        return roomRepository.findByStatusOrderByRoomNoAsc(
                        Room.RoomStatus.Occupied
                )
                .stream()
                .map(Room::getRoomNo)
                .collect(Collectors.toList());
    }

    // Update room status
    @Transactional
    public Optional<Room> updateRoomStatus(
            Integer roomNo,
            Room.RoomStatus newStatus) {

        Optional<Room> optionalRoom =
                roomRepository.findById(roomNo);

        if (optionalRoom.isEmpty()) {
            return Optional.empty();
        }

        Room room = optionalRoom.get();
        room.setStatus(newStatus);

        Room updatedRoom = roomRepository.save(room);

        return Optional.of(updatedRoom);
    }
}