package com.smart.hotel.service;

import com.smart.hotel.entity.CheckIn;

import java.util.List;

public interface CheckInService {
    void checkInGuest(CheckIn checkIn);
    List<CheckIn> getAllCheckIns();
    CheckIn getCheckInById(Long id);
    void checkOutGuest(Long id);
}
