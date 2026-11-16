/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelmanagement.model;

/**
 *
 * @author Nived
 */

public class Room {

    private int roomNo;
    private double price;
    private String status;

    public Room() {
    }

    public Room(int roomNo, double price, String status) {
        this.roomNo = roomNo;
        this.price = price;
        this.status = status;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNo=" + roomNo +
                ", price=" + price +
                ", status='" + status + '\'' +
                '}';
    }
}