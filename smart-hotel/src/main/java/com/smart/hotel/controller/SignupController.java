package com.smart.hotel.controller;

import com.smart.hotel.entity.AppUser;
import com.smart.hotel.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(
            @ModelAttribute("user") AppUser user,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model) {

        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "signup";
        }

        if (userService.emailExists(user.getEmail())) {
            model.addAttribute("error", "Email already registered.");
            return "signup";
        }

        userService.registerCustomer(user);
        return "redirect:/login?signupSuccess";
    }
}
