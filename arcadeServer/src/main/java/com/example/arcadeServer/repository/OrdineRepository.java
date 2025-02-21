package com.example.arcadeServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arcadeServer.model.Ordine;


public interface OrdineRepository extends JpaRepository<Ordine, Long>
{

}
