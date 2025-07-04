package com.smart.hotel.controller;

import com.smart.hotel.entity.Bill;
import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.repository.CheckInRepository;
import com.smart.hotel.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private CheckInRepository checkInRepository;

    // ✅ Generate bill for a given check-in
    @GetMapping("/bill/generate/{checkInId}")
    public String generateBill(@PathVariable Long checkInId, Model model) {
        CheckIn checkIn = checkInRepository.findById(checkInId).orElse(null);

        if (checkIn == null) {
            model.addAttribute("error", "Check-In not found for ID: " + checkInId);
            return "error"; // Or use a proper error page
        }

        Bill bill = billService.generateBill(checkIn);
        model.addAttribute("bill", bill);

        return "bill/bill_view";
    }

    // ✅ View all generated bills
    @GetMapping("/bills")
    public String viewAllBills(Model model) {
        model.addAttribute("bills", billService.getAllBills());
        return "bill/bill_list";
    }

    // ✅ View a specific bill by ID
    @GetMapping("/bills/{id}")
    public String viewBillById(@PathVariable Long id, Model model) {
        Bill bill = billService.getBillById(id);
        if (bill == null) {
            model.addAttribute("error", "Bill not found for ID: " + id);
            return "error";
        }
        model.addAttribute("bill", bill);
        return "bill/bill_view";
    }
}
