
package com.nived.hotel.hotelmanageapiserver.repository;

import com.nived.hotel.hotelmanageapiserver.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    // Get rooms by status
    List<Room> findByStatus(Room.RoomStatus status);

    // Get available room records
    List<Room> findByStatusOrderByRoomNoAsc(Room.RoomStatus status);
}