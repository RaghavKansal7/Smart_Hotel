package com.smart.hotel.repository;

import com.smart.hotel.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    List<FoodItem> findByRoomNumber(String roomNumber);  // ✅ correct method
}
