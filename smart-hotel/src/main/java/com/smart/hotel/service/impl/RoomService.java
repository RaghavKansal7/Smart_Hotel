package com.smart.hotel.service.impl;


import com.smart.hotel.DTO.AvailableRoomDTO;
import com.smart.hotel.entity.Room;
import com.smart.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<AvailableRoomDTO> getAvailableRoomsGrouped() {
        return roomRepository.findAll()
                .stream()
                .filter(Room::isAvailable)
                .collect(Collectors.groupingBy(
                        room -> new AbstractMap.SimpleEntry<>(room.getType(), room.getPrice()),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 0) // just in case
                .map(entry -> new AvailableRoomDTO(
                        entry.getKey().getKey(),
                        entry.getKey().getValue(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());
    }


}

