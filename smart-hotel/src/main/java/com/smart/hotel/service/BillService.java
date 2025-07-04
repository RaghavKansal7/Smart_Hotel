package com.smart.hotel.service;

import com.smart.hotel.entity.Bill;
import com.smart.hotel.entity.CheckIn;

import java.util.List;

public interface BillService {
    Bill generateBill(CheckIn checkIn);
    List<Bill> getAllBills();
    Bill getBillById(Long id);
}
