package com.example.arcadeServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arcadeServer.model.DataEntity;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {
    List<DataEntity> findByStatus(String status);

	List<DataEntity> findAllById(String string);
}
