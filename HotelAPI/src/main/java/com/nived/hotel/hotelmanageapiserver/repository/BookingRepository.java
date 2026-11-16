package com.nived.hotel.hotelmanageapiserver.repository;

import com.nived.hotel.hotelmanageapiserver.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository
        extends JpaRepository<Booking, Integer> {

    // Find active bookings for a room
    Optional<Booking> findByRoomNoAndCheckOutIsNull(
            Integer roomNo
    );

    // Find all active bookings
    List<Booking> findByCheckOutIsNull();

    // Find all bookings for a customer
    List<Booking> findByCustomerId(Integer customerId);
}