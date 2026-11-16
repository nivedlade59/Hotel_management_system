/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.model;

/**
 *
 * @author Nived
 */

import java.sql.Date;

public class Booking {

    private int bookingId;
    private int customerId;
    private int roomNo;
    private Date checkIn;
    private Date checkOut;

    public Booking() {
    }

    public Booking(int bookingId, int customerId, int roomNo,
                   Date checkIn, Date checkOut) {

        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomNo = roomNo;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

}