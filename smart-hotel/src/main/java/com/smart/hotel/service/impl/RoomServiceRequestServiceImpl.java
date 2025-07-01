package com.smart.hotel.service.impl;

import com.smart.hotel.entity.RoomServiceRequest;
import com.smart.hotel.repository.RoomServiceRequestRepository;
import com.smart.hotel.service.RoomServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceRequestServiceImpl implements RoomServiceRequestService {

    @Autowired
    private RoomServiceRequestRepository repo;

    @Override
    public void saveRequest(RoomServiceRequest request) {
        repo.save(request);
    }

    @Override
    public List<RoomServiceRequest> getAllRequests() {
        return repo.findAll();
    }

    @Override
    public void deleteRequest(Long id) {
        repo.deleteById(id);
    }

    @Override
    public RoomServiceRequest getRequestById(Long id) {
        return repo.findById(id).orElse(null);
    }
}
