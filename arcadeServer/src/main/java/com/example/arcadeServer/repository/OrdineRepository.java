package com.example.arcadeServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arcadeServer.model.Ordine;


public interface OrdineRepository extends JpaRepository<Ordine, Long>
{

	List<Ordine> findOrdiniByUtenteId(Long id);

}
