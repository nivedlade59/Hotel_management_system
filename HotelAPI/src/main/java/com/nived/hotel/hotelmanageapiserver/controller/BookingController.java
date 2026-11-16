package com.nived.hotel.hotelmanageapiserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import com.nived.hotel.hotelmanageapiserver.dto.AllotRoomRequest;
import com.nived.hotel.hotelmanageapiserver.entity.Booking;
import com.nived.hotel.hotelmanageapiserver.service.BookingService;
import com.nived.hotel.hotelmanageapiserver.dto.BillDetailsResponse;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    // Get all bookings
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Get active bookings
    @GetMapping("/active")
    public List<Booking> getActiveBookings() {
        return bookingService.getActiveBookings();
    }

    // Allot a room
    @PostMapping("/allot")
    public Booking allotRoom(
            @RequestBody AllotRoomRequest request) {

        return bookingService.allotRoom(request);
    }

    // Check out a booking
    @PatchMapping("/{bookingId}/checkout")
    public Booking checkout(
            @PathVariable Integer bookingId) {

        return bookingService.checkout(bookingId);
    }

    @GetMapping("/bill/{roomNo}")
    public BillDetailsResponse getBillDetails(
            @PathVariable Integer roomNo) {

        return bookingService.getBillDetails(roomNo);
    }
}