package com.smart.hotel.controller;

import com.smart.hotel.entity.Room;
import com.smart.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public String viewRooms(Model model) {
        model.addAttribute("roomList", roomService.getAllRooms());
        return "rooms";
    }

    @GetMapping("/add")
    public String addRoomForm(Model model) {
        model.addAttribute("room", new Room());
        return "add_room";
    }

    @PostMapping("/save")
    public String saveRoom(@ModelAttribute Room room) {
        roomService.saveRoom(room);
        return "redirect:/rooms";
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String editRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.getRoomById(id);
        model.addAttribute("room", room);
        return "add_room";
    }
}
