package com.smart.hotel.service.impl;

import com.smart.hotel.entity.Bill;
import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.entity.FoodItem;
import com.smart.hotel.entity.Room;
import com.smart.hotel.repository.BillRepository;
import com.smart.hotel.repository.FoodItemRepository;
import com.smart.hotel.repository.RoomRepository;
import com.smart.hotel.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Autowired
    private FoodItemRepository foodRepo;

    @Override
    public Bill generateBill(CheckIn checkIn) {
        Bill bill = new Bill();
        bill.setGuestName(checkIn.getGuestName());
        bill.setRoomNumber(checkIn.getRoomNumber());
        bill.setCheckInDate(checkIn.getCheckInDate());
        bill.setCheckOutDate(checkIn.getCheckOutDate());

        // Calculate number of days
        long days = ChronoUnit.DAYS.between(checkIn.getCheckInDate(), checkIn.getCheckOutDate());
        if (days <= 0) days = 1;

        // Get room price
        Room room = roomRepo.findByRoomNumber(checkIn.getRoomNumber());
        double roomRate = (room != null) ? room.getPrice() : 1500;
        double roomCharges = roomRate * days;

        // Get food charges
        List<FoodItem> foodItems = foodRepo.findByRoomNumber(checkIn.getRoomNumber());
        double foodCharges = foodItems.stream()
                .mapToDouble(FoodItem::getPrice)
                .sum();

        // Total
        bill.setRoomCharges(roomCharges);
        bill.setFoodCharges(foodCharges);
        bill.setTotalAmount(roomCharges + foodCharges);

        return billRepo.save(bill);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepo.findAll();
    }

    @Override
    public Bill getBillById(Long id) {
        return billRepo.findById(id).orElse(null);
    }
}