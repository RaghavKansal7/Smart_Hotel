package com.smart.hotel.controller;

import com.smart.hotel.DTO.AvailableRoomDTO;
import com.smart.hotel.entity.FoodItem;
import com.smart.hotel.entity.Room;
import com.smart.hotel.repository.FoodItemRepository;
import com.smart.hotel.repository.RoomRepository;
import com.smart.hotel.service.impl.BookingService;
import com.smart.hotel.service.impl.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private FoodItemRepository foodItemRepository;
@Autowired
   private BookingService bookingService;
    @GetMapping("/customer/home")
    public String customerHome() {
        return "customer-home";
    }

    @GetMapping("/customer/rooms")
    public String viewAvailableRooms(Model model) {
        List<AvailableRoomDTO> rooms = roomService.getAvailableRoomsGrouped();
        model.addAttribute("rooms", rooms);
        return "available-rooms";
    }

    @PostMapping("/customer/book")
    public String bookRoom(@RequestParam String roomNumber,
                           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
                           Model model) {
        bookingService.bookRoom(roomNumber, checkInDate);
        model.addAttribute("message", "Room booked successfully!");
        return "redirect:/customer/rooms";
    }


    @GetMapping("/customer/menu")
    public String viewMenu(Model model) {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        model.addAttribute("foodItems", foodItems);
        return "menu";
    }
}


