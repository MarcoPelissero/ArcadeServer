package com.example.arcadeServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.model.Utente;
import com.example.arcadeServer.repository.UtenteRepository;
import com.example.arcadeServer.exception.ResourceNotFoundException;


//Annotazione che indica che questa classe è un controller REST e gestisce le richieste HTTP
	@RestController
	// Imposta il path base per tutte le richieste gestite da questo controller: "/authors"
	@RequestMapping("/utenti")
public class UtenteController
{

	
	

	    // Iniezione automatica del repository per interagire con il database
	    @Autowired
	    private UtenteRepository utenteRepository;

	    // Metodo per ottenere la lista di tutti gli autori
	    // Mappato sulla richiesta HTTP GET all'endpoint "/authors"
	    @GetMapping
	    public List<Utente> getAllUtenti() {
	        // Restituisce tutti gli autori presenti nel database
	        return utenteRepository.findAll();
	    }
	    
	    @PostMapping
	    public Utente createUtente(@RequestBody Utente utente) {
	        // Il parametro @RequestBody converte il JSON della richiesta in un oggetto Author
	        // Salva il nuovo autore nel database e restituisce l'oggetto salvato (inclusi eventuali campi generati, come l'ID)
	        return utenteRepository.save(utente);
	    }
	    @GetMapping("/{id}")
	    public Utente getAuthorById(@PathVariable Long id) {
	        // Cerca l'autore per ID nel database.
	        // Se l'autore non viene trovato, lancia una ResourceNotFoundException con il messaggio "Author not found"
	        return utenteRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
	    }

	    // Metodo per aggiornare un autore esistente
	    // Mappato sulla richiesta HTTP PUT all'endpoint "/authors/{id}"
	    @PutMapping("/{id}")
	    public Utente updateUtente(@PathVariable Long id, @RequestBody Utente utenteDetails) {
	        // Cerca l'autore da aggiornare per ID.
	        // Se l'autore non viene trovato, lancia una ResourceNotFoundException
	    	Utente utente = utenteRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

	        // Aggiorna il campo "name" dell'autore con il nuovo valore fornito nel corpo della richiesta
	        utente.setNome(utenteDetails.getNome());

	        // Salva l'autore aggiornato nel database e restituisce l'oggetto aggiornato
	        return utenteRepository.save(utente);
	    }

	    // Metodo per eliminare un autore esistente
	    // Mappato sulla richiesta HTTP DELETE all'endpoint "/authors/{id}"
	    @DeleteMapping("/{id}")
	    public void deleteUtente(@PathVariable Long id) {
	        // Cerca l'autore da eliminare per ID.
	        // Se l'autore non viene trovato, lancia una ResourceNotFoundException
	        Utente utente = utenteRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

	        // Elimina l'autore trovato dal database
	        utenteRepository.delete(utente);
	    }
}
