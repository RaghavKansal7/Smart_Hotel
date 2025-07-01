package com.smart.hotel.controller;

import com.smart.hotel.entity.FoodItem;
import com.smart.hotel.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    private FoodItemRepository foodRepo;

    @GetMapping("/add")
    public String addFoodForm(Model model) {
        model.addAttribute("food", new FoodItem());
        return "add_food_item";
    }

    @PostMapping("/save")
    public String saveFood(@ModelAttribute("food") FoodItem foodItem) {
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
