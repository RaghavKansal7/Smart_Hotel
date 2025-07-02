package com.smart.hotel.repository;

import com.smart.hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    // You can add custom query methods here if needed
}
