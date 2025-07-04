package com.smart.hotel.controller;

import com.smart.hotel.entity.Bill;
import com.smart.hotel.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    // Already working
    @GetMapping("/bill/generate/{checkInId}")
    public String generateBill(@PathVariable Long checkInId, Model model) {
        // ... your existing bill generation logic
        return "bill/bill_view";
    }

    // ✅ New method to view all bills
    @GetMapping("/bills")
    public String viewAllBills(Model model) {
        model.addAttribute("bills", billService.getAllBills());
        return "bill/bill_list"; // ← should match the Thymeleaf file name
    }

    // ✅ Optional: View a single bill
    @GetMapping("/bills/{id}")
    public String viewBillById(@PathVariable Long id, Model model) {
        Bill bill = billService.getBillById(id);
        model.addAttribute("bill", bill);
        return "bill/bill_view";
    }
}
