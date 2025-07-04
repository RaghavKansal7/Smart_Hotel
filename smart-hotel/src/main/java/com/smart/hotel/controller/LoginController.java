package com.smart.hotel.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        if (error != null) model.addAttribute("error", "Invalid username or password.");
        if (logout != null) model.addAttribute("message", "You have been logged out.");
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model,
                        HttpServletRequest request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(token);

            if (auth.isAuthenticated()) {
                // ✅ This step is important: manually set authentication in the security context
                SecurityContextHolder.getContext().setAuthentication(auth);

                // ✅ Save context to session
                HttpSession session = request.getSession(true);
                session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

                return "redirect:/dashboard";
            }
        } catch (AuthenticationException e) {
            model.addAttribute("error", "Invalid username or password.");
        }

        return "login";
    }
}
