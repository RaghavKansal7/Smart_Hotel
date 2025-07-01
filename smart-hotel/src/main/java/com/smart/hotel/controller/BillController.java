package com.smart.hotel.controller;

import com.smart.hotel.entity.Bill;
import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.service.BillService;
import com.smart.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private BillService billService;

    @GetMapping("/generate/{checkInId}")
    public String generateBill(@PathVariable Long checkInId, Model model) {
        CheckIn checkIn = checkInService.getCheckInById(checkInId);

        if (checkIn.getStatus().equals("Checked In")) {
            model.addAttribute("error", "Guest is still checked in. Checkout required to generate bill.");
            return "redirect:/checkin/list";
        }

        Bill bill = billService.generateBill(checkIn);
        model.addAttribute("bill", bill);
        return "bill/bill_view";
    }
}
