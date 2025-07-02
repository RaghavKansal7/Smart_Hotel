package com.smart.hotel.DTO;

public class AvailableRoomDTO {
    private String roomNumber;
    private String type;
    private double price;
    private long count;

    public AvailableRoomDTO(String roomNumber, double price, long count) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.price = price;
        this.count = count;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public long getCount() {
        return count;
    }
}

