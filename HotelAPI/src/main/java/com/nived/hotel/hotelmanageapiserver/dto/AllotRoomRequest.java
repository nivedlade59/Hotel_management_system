package com.nived.hotel.hotelmanageapiserver.dto;

public class AllotRoomRequest {

    private Integer customerId;
    private Integer roomNo;

    public AllotRoomRequest() {
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }
}