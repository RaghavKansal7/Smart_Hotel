package com.smart.hotel.controller;

import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    // Display form and list
    @GetMapping("/list")
    public String showCheckInList(Model model) {
        model.addAttribute("checkin", new CheckIn()); // for form
        List<CheckIn> checkIns = checkInService.getAllCheckIns(); // for table
        model.addAttribute("checkins", checkIns);
        return "checkin/checkin_list";
    }

    // Handle form submission
    @PostMapping("/add")
    public String addCheckIn(@ModelAttribute("checkin") CheckIn checkIn) {
        checkInService.checkInGuest(checkIn); // sets date + status internally
        return "redirect:/checkin/list";
    }

    // Checkout guest
    @GetMapping("/checkout/{id}")
    public String checkout(@PathVariable Long id) {
        checkInService.checkOutGuest(id);
        return "redirect:/checkin/list";
    }
}
