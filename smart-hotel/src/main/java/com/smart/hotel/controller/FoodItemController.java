package com.smart.hotel.controller;

import com.smart.hotel.entity.CheckIn;
import com.smart.hotel.entity.FoodItem;
import com.smart.hotel.repository.CheckInRepository;
import com.smart.hotel.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    private FoodItemRepository foodRepo;

    @Autowired
    private CheckInRepository checkInRepo;  // ✅ Needed to get active rooms

    @GetMapping("/add")
    public String addFoodForm(Model model) {
        model.addAttribute("food", new FoodItem());

        // ✅ Get all rooms with "Checked In" status (to show in dropdown)
        List<String> activeRoomNumbers = checkInRepo.findByStatus("Checked In")
                .stream()
                .map(CheckIn::getRoomNumber)
                .distinct()
                .collect(Collectors.toList());

        model.addAttribute("activeRooms", activeRoomNumbers);
        return "add_food_item";
    }

    @PostMapping("/save")
    public String saveFood(@ModelAttribute("food") FoodItem foodItem) {
        System.out.println("Saving food: " + foodItem.getItemName() + ", Room: " + foodItem.getRoomNumber() + ", Status: " + foodItem.getStatus() + ", Price: " + foodItem.getPrice());
        foodRepo.save(foodItem);
        return "redirect:/food/list";
    }


    @GetMapping("/list")
    public String viewFoodItems(Model model) {
        model.addAttribute("foodList", foodRepo.findAll());
        return "food_items";
    }

    @GetMapping("/delete/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodRepo.deleteById(id);
        return "redirect:/food/list";
    }
}
