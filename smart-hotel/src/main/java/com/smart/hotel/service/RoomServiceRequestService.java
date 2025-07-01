package com.smart.hotel.service;

import com.smart.hotel.entity.RoomServiceRequest;

import java.util.List;

public interface RoomServiceRequestService {
    void saveRequest(RoomServiceRequest request);
    List<RoomServiceRequest> getAllRequests();
    void deleteRequest(Long id);
    RoomServiceRequest getRequestById(Long id);
}
