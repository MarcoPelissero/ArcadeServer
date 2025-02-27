package com.example.arcadeServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.exception.ResourceNotFoundException;
import com.example.arcadeServer.model.Servizio;
import com.example.arcadeServer.model.Utente;
import com.example.arcadeServer.repository.ServizioRepository;
import com.example.arcadeServer.repository.UtenteRepository;


@RestController
@RequestMapping("/servizi")
@CrossOrigin(origins = {})
public class ServizioController
{
	@Autowired

    private ServizioRepository servizioRepository;
	
	@Autowired 
	private UtenteRepository utenteRepo;

    /**
     * Ottiene tutti i servizi
     * @return Lista di tutti i servizi
     */
    @GetMapping
    public List<Servizio> getAllServizi() {
        return servizioRepository.findAll();
    }

    // Metodo per creare un nuovo libro
    // Mappato sulla richiesta HTTP POST all'endpoint "/servizios"
    @PostMapping("/{id}")
    public Servizio createServizio(@PathVariable Long id, @RequestBody Servizio servizio) {
    	Utente utente = utenteRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
    	servizio.setUtente(utente);
        // Salva il nuovo libro nel database e restituisce l'oggetto salvato (potrebbe includere l'ID generato)

        return servizioRepository.save(servizio);
    }

    /**
     * Ottiene un servizio specifico tramite ID
     * @param id Identificativo del servizio da cercare
     * @return Il servizio con l'ID specificato
     */
    @GetMapping("/{id}")
    public Servizio getServizioById(@PathVariable Long id) {
        return servizioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servizio non trovato con id: " + id));
    }

    /**
     * Aggiorna un servizio esistente
     * @param id ID del servizio da aggiornare
     * @param servizioDetails Oggetto contenente i dettagli da aggiornare
     * @return Servizio aggiornato
     */
    @PutMapping("/{id}")
    public Servizio updateServizio(@PathVariable Long id, @RequestBody Servizio servizioDetails) {
        Servizio servizio = servizioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servizio non trovato con id: " + id));

        // Aggiornamento dei dettagli del servizio
        servizio.setNome(servizioDetails.getNome());
        servizio.setDescrizione(servizioDetails.getDescrizione());
        servizio.setPrezzo(servizioDetails.getPrezzo());
        servizio.setCategoria(servizioDetails.getCategoria());

        // Salva il servizio aggiornato
        return servizioRepository.save(servizio);
    }

    /**
     * Elimina un servizio esistente
     * @param id Identificativo del servizio da eliminare
     */
    @DeleteMapping("/{id}")
    public void deleteServizio(@PathVariable Long id) {
        Servizio servizio = servizioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servizio non trovato con id: " + id));

        // Elimina il servizio
        servizioRepository.delete(servizio);
    }
}