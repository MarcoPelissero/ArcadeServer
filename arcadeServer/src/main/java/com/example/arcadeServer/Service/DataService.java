package com.example.arcadeServer.Service;





import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.arcadeServer.exception.ResourceNotFoundException;
import com.example.arcadeServer.model.DataEntity;
import com.example.arcadeServer.repository.DataRepository;

@Service
public class DataService {


	 @Autowired
	    private DataRepository dataRepository;

	    public DataEntity saveRequest(DataEntity dataEntity) {
	        return dataRepository.save(dataEntity);
	    }

	    public DataEntity getRequestById(Long id) {
	        return dataRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Richiesta non trovata"));
	    }

	    public List<DataEntity> getAllRequests() {
	        return dataRepository.findAll();
	    }

	    public List<DataEntity> getAcceptedRequests() {
	        return dataRepository.findAllById("ACCEPTED");
	    }

	    public List<DataEntity> getPendingRequests() {
	        return dataRepository.findAllById("PENDING");
	    }
	}
