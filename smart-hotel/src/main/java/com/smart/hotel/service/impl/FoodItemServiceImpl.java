package com.smart.hotel.service.impl;

import com.smart.hotel.entity.FoodItem;
import com.smart.hotel.repository.FoodItemRepository;
import com.smart.hotel.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository repo;

    @Override
    public FoodItem saveFoodItem(FoodItem item) {
        return repo.save(item);
    }

    @Override
    public List<FoodItem> getAllFoodItems() {
        return repo.findAll();
    }

    @Override
    public FoodItem getFoodItemById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void deleteFoodItem(Long id) {
        repo.deleteById(id);
    }

    @Override
    public FoodItem updateFoodItem(FoodItem item) {
        return repo.save(item);
    }
}
