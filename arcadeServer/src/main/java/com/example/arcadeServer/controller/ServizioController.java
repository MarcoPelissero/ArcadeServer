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
import com.example.arcadeServer.repository.ServizioRepository;

@RestController
@RequestMapping("/servizi")
@CrossOrigin(origins = {})
public class ServizioController
{
	@Autowired
    private ServizioRepository servizioRepository;

    // Metodo per ottenere la lista di tutti i libri
    // Mappato sulla richiesta HTTP GET all'endpoint "/servizios"
    @GetMapping
    public List<Servizio> getAllServizios() {
        // Utilizza il repository per recuperare e restituire tutti i libri presenti nel database
        return servizioRepository.findAll();
    }

    // Metodo per creare un nuovo libro
    // Mappato sulla richiesta HTTP POST all'endpoint "/servizios"
    @PostMapping
    public Servizio createServizio(@RequestBody Servizio servizio) {
    	System.out.println(servizio.getUtente());
        // Salva il nuovo libro nel database e restituisce l'oggetto salvato (potrebbe includere l'ID generato)
        return servizioRepository.save(servizio);
    }

    // Metodo per ottenere un servizio specifico identificato dal suo ID
    // Mappato sulla richiesta HTTP GET all'endpoint "/servizios/{id}"
    @GetMapping("/{id}")
    public Servizio getServizioById(@PathVariable Long id) {
        // Cerca il servizio per ID, se non trovato lancia un'eccezione ResourceNotFoundException
        return servizioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servizio not found"));
    }

    // Metodo per aggiornare un servizio esistente
    // Mappato sulla richiesta HTTP PUT all'endpoint "/servizios/{id}"
    @PutMapping("/{id}")
    public Servizio updateServizio(@PathVariable Long id, @RequestBody Servizio servizioDetails) {
        // Cerca il servizio da aggiornare per ID, se non trovato lancia un'eccezione
        Servizio servizio = servizioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servizio not found"));

        // Aggiorna i campi del servizio con i nuovi dati forniti
        servizio.setNome(servizioDetails.getNome());
        servizio.setDescrizione(servizioDetails.getDescrizione());
        servizio.setPrezzo(servizioDetails.getPrezzo());

        // Salva le modifiche nel database e restituisce il servizio aggiornato
        return servizioRepository.save(servizio);
    }

    // Metodo per eliminare un servizio
    // Mappato sulla richiesta HTTP DELETE all'endpoint "/servizios/{id}"
    @DeleteMapping("/{id}")
    public void deleteServizio(@PathVariable Long id) {
        // Cerca il libro da eliminare per ID, se non trovato lancia un'eccezione
        Servizio servizio = servizioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servizio not found"));

        // Elimina il servizio dal database
        servizioRepository.delete(servizio);
    }

}
