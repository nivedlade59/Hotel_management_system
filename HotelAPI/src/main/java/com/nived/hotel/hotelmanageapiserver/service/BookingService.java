package com.nived.hotel.hotelmanageapiserver.service;

import com.nived.hotel.hotelmanageapiserver.dto.AllotRoomRequest;
import com.nived.hotel.hotelmanageapiserver.dto.BillDetailsResponse;
import com.nived.hotel.hotelmanageapiserver.entity.Booking;
import com.nived.hotel.hotelmanageapiserver.entity.Customer;
import com.nived.hotel.hotelmanageapiserver.entity.Room;
import com.nived.hotel.hotelmanageapiserver.repository.BookingRepository;
import com.nived.hotel.hotelmanageapiserver.repository.CustomerRepository;
import com.nived.hotel.hotelmanageapiserver.repository.RoomRepository;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;
    private final CustomerRepository customerRepository;

    // Constructor
    public BookingService(
            BookingRepository bookingRepository,
            RoomRepository roomRepository,
            CustomerRepository customerRepository) {

        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
        this.customerRepository = customerRepository;
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get all active bookings
    public List<Booking> getActiveBookings() {
        return bookingRepository.findByCheckOutIsNull();
    }

    // Allot a room to a customer
    public Booking allotRoom(AllotRoomRequest request) {

        Integer customerId = request.getCustomerId();
        Integer roomNo = request.getRoomNo();

        // Check whether the room exists
        Room room = roomRepository.findById(roomNo)
                .orElseThrow(() ->
                        new RuntimeException("Room not found: " + roomNo));

        // Check whether the room is available
        if (room.getStatus() != Room.RoomStatus.Available) {
            throw new RuntimeException(
                    "Room is not available: " + roomNo);
        }

        // Check whether the customer exists
        customerRepository.findById(customerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found: " + customerId));

        // Create booking
        Booking booking = new Booking();

        booking.setCustomerId(customerId);
        booking.setRoomNo(roomNo);
        booking.setCheckIn(LocalDate.now());

        // Save booking
        Booking savedBooking = bookingRepository.save(booking);

        // Update room status
        room.setStatus(Room.RoomStatus.Occupied);
        roomRepository.save(room);

        return savedBooking;
    }

    // Checkout a booking
    public Booking checkout(Integer bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Booking not found: " + bookingId));

        // Check whether already checked out
        if (booking.getCheckOut() != null) {
            throw new RuntimeException(
                    "Booking has already been checked out");
        }

        // Set checkout date
        booking.setCheckOut(LocalDate.now());

        Booking savedBooking = bookingRepository.save(booking);

        // Make room available again
        Room room = roomRepository.findById(booking.getRoomNo())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found: " + booking.getRoomNo()));

        room.setStatus(Room.RoomStatus.Available);
        roomRepository.save(room);

        return savedBooking;
    }

    // Get bill details for an active booking by room number
    public BillDetailsResponse getBillDetails(Integer roomNo) {

        // Find active booking for this room
        Booking booking = bookingRepository
                .findByRoomNoAndCheckOutIsNull(roomNo)
                .orElseThrow(() ->
                        new RuntimeException(
                                "No active booking found for room: "
                                        + roomNo));

        // Get customer details
        Customer customer = customerRepository
                .findById(booking.getCustomerId())
                .orElseThrow(() ->
                        new RuntimeException(
                                "Customer not found: "
                                        + booking.getCustomerId()));

        // Get room details
        Room room = roomRepository.findById(roomNo)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Room not found: " + roomNo));

        // Validate number of days
        Integer days = customer.getDays();

        if (days == null || days <= 0) {
            throw new RuntimeException(
                    "Customer's stay duration is missing or invalid");
        }

        // Calculate total bill
        BigDecimal total = room.getPrice()
                .multiply(BigDecimal.valueOf(days));

        // Prepare response
        BillDetailsResponse response = new BillDetailsResponse();

        response.setBookingId(booking.getBookingId());
        response.setCustomerId(customer.getCustomerId());
        response.setCustomerName(customer.getCustomerName());
        response.setPhone(customer.getPhone());
        response.setAddress(customer.getAddress());
        response.setDays(days);
        response.setRoomNo(room.getRoomNo());
        response.setPrice(room.getPrice());
        response.setTotal(total);

        return response;
    }
}