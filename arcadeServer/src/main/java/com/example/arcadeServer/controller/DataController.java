package com.example.arcadeServer.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.Service.DataService;
import com.example.arcadeServer.model.DataEntity;

@RestController
public class DataController {
	 @Autowired
	    private DataService dataService;

	    @GetMapping("/api/requests")
	    public List<DataEntity> getRequests() {
	        return dataService.getAllRequests();
	    }

	    @GetMapping("/api/requests/accepted")
	    public List<DataEntity> getAcceptedRequests() {
	        return dataService.getAcceptedRequests();
	    }

	    @GetMapping("/api/requests/pending")
	    public List<DataEntity> getPendingRequests() {
	        return dataService.getPendingRequests();
	    }
	}