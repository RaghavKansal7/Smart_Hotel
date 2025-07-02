package com.smart.hotel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/dashboard")
    public String dashboard(Authentication auth) {
        String role = auth.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_ADMIN")) return "redirect:/admin/home";
        if (role.equals("ROLE_RECEPTIONIST")) return "redirect:/receptionist/home";
        if (role.equals("ROLE_CUSTOMER")) return "redirect:/customer/home";
        return "redirect:/login?error";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}

