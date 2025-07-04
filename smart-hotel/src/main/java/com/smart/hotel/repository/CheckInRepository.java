package com.smart.hotel.repository;

import com.smart.hotel.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CheckInRepository extends JpaRepository<CheckIn, Long> {
    List<CheckIn> findByRoomNumberAndStatus(String roomNumber, String status);
    List<CheckIn> findByStatus(String status);

}
