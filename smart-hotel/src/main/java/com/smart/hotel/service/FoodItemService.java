package com.smart.hotel.service;

import com.smart.hotel.entity.FoodItem;

import java.util.List;

public interface FoodItemService {
    FoodItem saveFoodItem(FoodItem item);
    List<FoodItem> getAllFoodItems();
    FoodItem getFoodItemById(Long id);
    void deleteFoodItem(Long id);
    FoodItem updateFoodItem(FoodItem item);
}
