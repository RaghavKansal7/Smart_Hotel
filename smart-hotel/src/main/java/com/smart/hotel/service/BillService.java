package com.smart.hotel.service;

import com.smart.hotel.entity.Bill;
import com.smart.hotel.entity.CheckIn;

public interface BillService {
    Bill generateBill(CheckIn checkIn);
}
