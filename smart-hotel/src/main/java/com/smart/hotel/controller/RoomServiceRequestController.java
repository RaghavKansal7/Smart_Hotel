package com.smart.hotel.controller;

import com.smart.hotel.entity.RoomServiceRequest;
import com.smart.hotel.service.RoomServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room-service")
public class RoomServiceRequestController {

    @Autowired
    private RoomServiceRequestService requestService;

    @GetMapping("/list")
    public String viewRequests(Model model) {
        model.addAttribute("requests", requestService.getAllRequests());
        return "room_service/view_request";
    }

    @GetMapping("/add")
    public String addRequestForm(Model model) {
        model.addAttribute("request", new RoomServiceRequest());
        return "room_service/add_request";
    }

    @PostMapping("/save")
    public String saveRequest(@ModelAttribute RoomServiceRequest request) {
        requestService.saveRequest(request);
        return "redirect:/room-service/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRequest(@PathVariable Long id) {
        requestService.deleteRequest(id);
        return "redirect:/room-service/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        RoomServiceRequest request = requestService.getRequestById(id);
        model.addAttribute("request", request);
        return "room_service/update_request";
    }

    @PostMapping("/update")
    public String updateRequest(@ModelAttribute("request") RoomServiceRequest request) {
        requestService.saveRequest(request);
        return "redirect:/room-service/list";
    }
}
