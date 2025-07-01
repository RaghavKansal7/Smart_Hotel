package com.smart.hotel.service;

import com.smart.hotel.entity.Room;
import java.util.List;

public interface RoomService {
    Room saveRoom(Room room);
    List<Room> getAllRooms();
    Room getRoomById(Long id);
    void deleteRoom(Long id);
}
