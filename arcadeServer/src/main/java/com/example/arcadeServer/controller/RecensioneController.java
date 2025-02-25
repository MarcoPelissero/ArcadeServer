package com.example.arcadeServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.model.Recensione;
import com.example.arcadeServer.repository.RecensioneRepository;
import com.example.arcadeServer.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/recensioni")
@CrossOrigin(origins = {})
public class RecensioneController
{
	
	@Autowired
	private RecensioneRepository recensioneRepository;
	 
	@PostMapping
	public Recensione createRecensione(@RequestBody Recensione recensione) {
	        // Salva il nuovo libro nel database e restituisce l'oggetto salvato (potrebbe includere l'ID generato)
	        return recensioneRepository.save(recensione);
	    }
	 @GetMapping("/{voto}")
	 public Recensione getRecensioneByVoto(@PathVariable int voto) {
	        // Cerca recensione per voto, se non trovato lancia un'eccezione ResourceNotFoundException
	        return recensioneRepository.findByVoto(voto)
	                .orElseThrow(() -> new ResourceNotFoundException("Recensione non trovata"));
	    }

}
