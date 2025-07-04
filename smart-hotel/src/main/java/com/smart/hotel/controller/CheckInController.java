package com.smart.hotel.controller;

import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.entity.Room;
import com.smart.hotel.repository.RoomRepository;
import com.smart.hotel.service.CheckInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/checkin")
public class CheckInController {

    @Autowired
    private CheckInService checkInService;

    @Autowired
    private RoomRepository roomRepo;

    @GetMapping("/list")
    public String showCheckInList(Model model) {
        model.addAttribute("checkin", new CheckIn()); // for form

        List<CheckIn> checkIns = checkInService.getAllCheckIns();
        model.addAttribute("checkins", checkIns); // for table

        // Get occupied room numbers
        List<String> occupiedRooms = checkIns.stream()
                .filter(c -> "Checked In".equalsIgnoreCase(c.getStatus()))
                .map(CheckIn::getRoomNumber)
                .toList();

        // Get available rooms (not occupied)
        List<Room> availableRooms = roomRepo.findAll().stream()
                .filter(r -> !occupiedRooms.contains(r.getRoomNumber()))
                .toList();

        model.addAttribute("availableRooms", availableRooms);
        return "checkin/checkin_list";
    }

    @PostMapping("/add")
    public String addCheckIn(@ModelAttribute("checkin") CheckIn checkIn) {
        checkInService.checkInGuest(checkIn);
        return "redirect:/checkin/list";
    }

    @GetMapping("/checkout/{id}")
    public String checkout(@PathVariable Long id) {
        checkInService.checkOutGuest(id);
        return "redirect:/checkin/list";
    }
}
