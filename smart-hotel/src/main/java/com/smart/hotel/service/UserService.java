package com.smart.hotel.service;

import com.smart.hotel.entity.User;
import com.smart.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public void registerUser(String username, String rawPassword) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        User user = new User(username, encodedPassword, "USER");
        userRepository.save(user);
    }

}