package com.smart.hotel.repository;

import com.smart.hotel.DTO.AvailableRoomDTO;
import com.smart.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNumber(String roomNumber);

}
