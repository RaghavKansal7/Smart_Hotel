package com.smart.hotel.service.impl;

import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.entity.FoodItem;
import com.smart.hotel.entity.Room;
import com.smart.hotel.repository.CheckInRepository;
import com.smart.hotel.repository.FoodItemRepository;
import com.smart.hotel.repository.RoomRepository;
import com.smart.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckInServiceImpl implements CheckInService {

    @Autowired
    private CheckInRepository checkInRepo;

    @Autowired
    private FoodItemRepository foodRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public void checkInGuest(CheckIn checkIn) {
        // Prevent double check-in on same room
        List<CheckIn> activeCheckIns = checkInRepo.findByRoomNumberAndStatus(checkIn.getRoomNumber(), "Checked In");
        if (!activeCheckIns.isEmpty()) {
            throw new IllegalStateException("Room " + checkIn.getRoomNumber() + " is already occupied.");
        }

        checkIn.setCheckInDate(LocalDate.now());
        checkIn.setStatus("Checked In");
        checkInRepo.save(checkIn);

        // Mark room as unavailable
        Room room = roomRepo.findByRoomNumber(checkIn.getRoomNumber());
        if (room != null) {
            room.setAvailable(false);
            roomRepo.save(room);
        }
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
        if (checkIn != null && "Checked In".equals(checkIn.getStatus())) {
            checkIn.setCheckOutDate(LocalDate.now());
            checkIn.setStatus("Checked Out");
            checkInRepo.save(checkIn);

            // Mark room available again
            Room room = roomRepo.findByRoomNumber(checkIn.getRoomNumber());
            if (room != null) {
                room.setAvailable(true);
                roomRepo.save(room);
            }
        }
    }
}
