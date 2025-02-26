package com.example.arcadeServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.arcadeServer.exception.ResourceNotFoundException;
import com.example.arcadeServer.model.Servizio;
import com.example.arcadeServer.repository.ServizioRepository;

@CrossOrigin(origins = "http://localhost:8080") // Consente richieste cross-origin dal frontend
@RestController // Indica che questa è una classe controller per REST
@RequestMapping("/servizi") // Mappatura del percorso base per tutte le richieste a "/servizi"
public class ServizioController {

    @Autowired
    private ServizioRepository servizioRepository;

    /**
     * Ottiene tutti i servizi
     * @return Lista di tutti i servizi
     */
    @GetMapping
    public List<Servizio> getAllServizi() {
        return servizioRepository.findAll();
    }

    /**
     * Crea un nuovo servizio
     * @param servizio Oggetto servizio da creare
     * @return Servizio creato
     */
    @PostMapping
    public Servizio createServizio(@RequestBody Servizio servizio) {
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
