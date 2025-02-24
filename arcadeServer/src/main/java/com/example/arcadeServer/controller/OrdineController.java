package com.example.arcadeServer.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.model.AuthUser;
import com.example.arcadeServer.model.Ordine;
import com.example.arcadeServer.model.Utente;
import com.example.arcadeServer.repository.AuthUserRepository;
import com.example.arcadeServer.repository.OrdineRepository;
import com.example.arcadeServer.repository.UtenteRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ordini")
public class OrdineController
{
	@Autowired
	private OrdineRepository ordineRepo;
	@Autowired
	private AuthUserRepository authRepo;
	@Autowired
	private UtenteRepository userRepository;

	/**
	 * 
	 * @return List di tutti gli ordini
	 * Metodo per ottenere tutti gli ordini (non utilizzabile da utenti)
	 */
	@GetMapping
	public ResponseEntity<List<Ordine>> ottieniTuttiOrdini()
	{
		List<Ordine> ordini = ordineRepo.findAll();
		return ResponseEntity.ok(ordini);
	}

	/**
	 * 
	 * @param ordine
	 * @return Response entity completa con i dettagli dell'ordine creato e messaggio di creazione
	 * Metodo per creare un nuovo ordine
	 */
	@PostMapping
	public ResponseEntity<Ordine> creaOrdine(@Valid @RequestBody Ordine ordine)
	{
		Ordine nuovoOrdine = ordineRepo.save(ordine);
		return new ResponseEntity<Ordine>(nuovoOrdine, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @param authorizationHeader valore dell'header HTTP
	 * @return response entity con body lista ordini e status ok se non ci sono errori, altrimenti uno status di non autorizzato
	 */
	@GetMapping("/dettagliOrdini")
	public ResponseEntity<Object> getOrdiniUtenteLoggato(@RequestHeader("Authorization") String authorizationHeader)
	{
		Map<String, String> result = new HashMap<String, String>();
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) // Verifica che l'header Authorization sia presente e abbia standard corretto
		{
			result.put("errore", "Header Authorization mancante o formato errato.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}
		String token = authorizationHeader.substring(7); // Estrae il token rimuovendo la stringa "Bearer "
		System.out.println("Token estratto: " + token); // Debug
		Optional<AuthUser> authUserOpt = authRepo.findByToken(token); // Cerca l'utente autenticato tramite il token
		if (!authUserOpt.isPresent())
		{
			result.put("errore", "Il token non è associato a nessun utente.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}
		String email = authUserOpt.get().getEmail(); // Recupera l'email associata al token
		System.out.println("Email associata al token: " + email); // Debug
		Optional<Utente> userOpt = userRepository.findByEmail(email); // Recupera l'eventuale utente dal database
		if (!userOpt.isPresent())
		{
			result.put("errore", "Non è presente alcun utente con l'email in uso.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}
		Utente user = userOpt.get();
		List<Ordine> ordini = ordineRepo.findOrdiniByUtenteId(user.getId()); // Recupera gli ordini dell'utente
		return ResponseEntity.ok(ordini);
	}
}

