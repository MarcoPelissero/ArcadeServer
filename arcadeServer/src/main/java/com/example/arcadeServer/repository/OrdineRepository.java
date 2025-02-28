package com.example.arcadeServer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.arcadeServer.model.Ordine;


public interface OrdineRepository extends JpaRepository<Ordine, Long>
{

	List<Ordine> findOrdiniByUtenteId(Long id);
	
	List<Ordine> findOrdiniByServizioId(Long id);
	
//	@Query("SELECT u FROM ordine u WHERE u.name = :name")
//    List<User> findByName(@Param("name") String name);

}
