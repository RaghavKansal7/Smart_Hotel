package com.smart.hotel.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class CheckIn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String guestName;

    private String roomNumber;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String status; // Checked In / Checked Out

    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getGuestName() { return guestName; }

    public void setGuestName(String guestName) { this.guestName = guestName; }

    public String getRoomNumber() { return roomNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public LocalDate getCheckInDate() { return checkInDate; }

    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate; }

    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
