package com.example.arcadeServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arcadeServer.model.DataEntity;

@Repository
public interface DataRepository extends JpaRepository<DataEntity, Long> {
}