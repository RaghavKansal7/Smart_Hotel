package com.smart.hotel.controller;

import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @GetMapping("/form")
    public String showCheckInForm(Model model) {
        model.addAttribute("checkIn", new CheckIn());
        return "checkin/checkin_form";
    }

    @PostMapping("/save")
    public String checkIn(@ModelAttribute CheckIn checkIn, Model model) {
        try {
            checkInService.checkInGuest(checkIn);
            return "redirect:/checkin/list";
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("checkIn", checkIn);
            return "checkin/checkin_form";
        }
    }


    @GetMapping("/list")
    public String listCheckIns(Model model) {
        model.addAttribute("checkIns", checkInService.getAllCheckIns());
        return "checkin/checkin_list";
    }

    @GetMapping("/checkout/{id}")
    public String showCheckoutForm(@PathVariable Long id, Model model) {
        model.addAttribute("checkIn", checkInService.getCheckInById(id));
        return "checkin/checkout_form";
    }

    @PostMapping("/checkout")
    public String checkOut(@RequestParam Long id) {
        checkInService.checkOutGuest(id);
        return "redirect:/checkin/list";
    }
}
