package com.smart.hotel.controller;

import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.entity.RoomServiceRequest;
import com.smart.hotel.repository.CheckInRepository;
import com.smart.hotel.service.RoomServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/roomservice")
public class RoomServiceController {

    @Autowired
    private RoomServiceRequestService service;

    @Autowired
    private CheckInRepository checkInRepository; // ✅ for active rooms

    @GetMapping("/list")
    public String viewAllRequests(Model model) {
        model.addAttribute("roomServiceRequest", new RoomServiceRequest());
        model.addAttribute("requests", service.getAllRequests());

        // ✅ get only "Checked In" rooms
        List<String> activeRooms = checkInRepository.findByStatus("Checked In")
                .stream()
                .map(CheckIn::getRoomNumber)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("activeRooms", activeRooms);

        return "roomservice/room_service_list";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute RoomServiceRequest request) {
        service.saveRequest(request);
        return "redirect:/roomservice/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        RoomServiceRequest request = service.getRequestById(id);
        model.addAttribute("roomServiceRequest", request);
        model.addAttribute("requests", service.getAllRequests());

        List<String> activeRooms = checkInRepository.findByStatus("Checked In")
                .stream()
                .map(CheckIn::getRoomNumber)
                .distinct()
                .collect(Collectors.toList());
        model.addAttribute("activeRooms", activeRooms);

        return "roomservice/room_service_list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteRequest(id);
        return "redirect:/roomservice/list";
    }
}
