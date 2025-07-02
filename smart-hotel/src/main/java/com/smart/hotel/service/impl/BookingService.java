package com.smart.hotel.service.impl;

import com.smart.hotel.entity.AppUser;
import com.smart.hotel.entity.Booking;
import com.smart.hotel.entity.Room;
import com.smart.hotel.repository.BookingRepository;
import com.smart.hotel.repository.RoomRepository;
import com.smart.hotel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public void bookRoom(String roomNumber, LocalDate checkInDate) {
        Room room = roomRepository.findByRoomNumber(roomNumber);
        // You can save a booking entry here (you likely have a Booking entity/table)
        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setCheckInDate(checkInDate);
        bookingRepository.save(booking);

        // Optionally mark room unavailable
        room.setAvailable(false);
        roomRepository.save(room);
    }

}

