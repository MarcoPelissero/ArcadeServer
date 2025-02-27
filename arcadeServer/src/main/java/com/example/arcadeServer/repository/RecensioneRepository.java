package com.example.arcadeServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.example.arcadeServer.model.Recensione;


public interface RecensioneRepository extends JpaRepository<Recensione, Long>
{

	Optional<Recensione> findByVoto(int voto);
	
	Optional<Recensione> findAllByServizioId(Long id);



}
