package com.example.arcadeServer.Service;





import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arcadeServer.model.DataEntity;
import com.example.arcadeServer.repository.DataRepository;

@Service
public class DataService {


    @Autowired
    private DataRepository requestRepository;

    public List<DataEntity> getAllRequests() {
        return requestRepository.findAll();
    }

    public List<DataEntity> getAcceptedRequests() {
        return requestRepository.findAll().stream()
                .filter(request -> "accepted".equalsIgnoreCase(request.getStatus()))
                .collect(Collectors.toList());
    }

    public List<DataEntity> getPendingRequests() {
        return requestRepository.findAll().stream()
                .filter(request -> "pending".equalsIgnoreCase(request.getStatus()))
                .collect(Collectors.toList());
    }
}