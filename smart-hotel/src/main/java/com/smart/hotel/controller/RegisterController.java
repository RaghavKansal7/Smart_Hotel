package com.smart.hotel.controller;

import com.smart.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    // Show the registration form
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "register";
    }

    // Handle registration form submit
    @PostMapping("/register")
    public String processRegister(@RequestParam String username,
                                  @RequestParam String password,
                                  Model model) {

        if (userService.userExists(username)) {
            model.addAttribute("error", "⚠ Username already exists");
            return "register";
        }

        userService.registerUser(username, password);
        return "redirect:/login?registered";
    }
}