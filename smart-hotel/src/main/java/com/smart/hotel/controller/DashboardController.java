package com.smart.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String showDashboard() {
        return "dashboard";
    }
}
