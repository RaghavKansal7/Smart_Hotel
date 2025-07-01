package com.smart.hotel.service.impl;

import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.repository.CheckInRepository;
import com.smart.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private CheckInRepository checkInRepo;

    @Override
    public void checkInGuest(CheckIn checkIn) {
        // ✅ Prevent double check-in on same room
        List<CheckIn> activeCheckIns = checkInRepo.findByRoomNumberAndStatus(checkIn.getRoomNumber(), "Checked In");
        if (!activeCheckIns.isEmpty()) {
            throw new IllegalStateException("Room " + checkIn.getRoomNumber() + " is already occupied.");
        }

        checkIn.setCheckInDate(LocalDate.now());
        checkIn.setStatus("Checked In");
        checkInRepo.save(checkIn);
    }

    @Override
    public List<CheckIn> getAllCheckIns() {
        return checkInRepo.findAll();
    }

    @Override
    public CheckIn getCheckInById(Long id) {
        return checkInRepo.findById(id).orElse(null);
    }

    @Override
    public void checkOutGuest(Long id) {
        CheckIn checkIn = getCheckInById(id);
        if (checkIn != null) {
            checkIn.setCheckOutDate(LocalDate.now());
            checkIn.setStatus("Checked Out");
            checkInRepo.save(checkIn);
        }
    }
}
