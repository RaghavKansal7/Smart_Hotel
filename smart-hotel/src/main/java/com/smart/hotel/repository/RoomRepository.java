package com.smart.hotel.repository;

import com.smart.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByAvailable(boolean available);

    // ✅ Updated to return a list
    Room findByRoomNumber(String roomNumber);

}
