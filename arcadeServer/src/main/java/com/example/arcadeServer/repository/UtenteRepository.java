package com.example.arcadeServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arcadeServer.model.Utente;


public interface UtenteRepository extends JpaRepository<Utente, Long>
{

	Optional<Utente> findByEmail(String email);

}
