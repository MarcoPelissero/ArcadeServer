package com.example.arcadeServer.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.arcadeServer.model.AuthUser;
import com.example.arcadeServer.model.Pagamento;
import com.example.arcadeServer.repository.AuthUserRepository;
import com.example.arcadeServer.repository.PagamentoRepository;
import java.util.Collections;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pagamenti")
@CrossOrigin(origins = {})
public class PagamentoController
{
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private AuthUserRepository authRepo;
	public ResponseEntity<List<Pagamento>> ottieniTuttiPagamenti()
	{
		List<Pagamento> pagamenti = pagamentoRepository.findAll();
		return ResponseEntity.ok(pagamenti);
	}
	
	//aggiungre un pagamento
	@PostMapping("/add")
	public Pagamento addPagamento(@RequestBody Pagamento pagamento, HttpServletRequest request) {
	    // Ottieni l'utente autenticato tramite il token
	    AuthUser authUser = getAuthenticatedUser(request);
	    if (authUser == null) {
	        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Non autorizzato");
	    }

	    // Verifica che i dati obbligatori siano presenti
	    if (pagamento.getMetodo() == null || pagamento.getMetodo().isEmpty()) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Metodo di pagamento obbligatorio");
	    }
	    if (pagamento.getImporto() <= 0) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'importo deve essere maggiore di zero");
	    }
	    if (pagamento.getOrdine() == null || pagamento.getOrdine().getId() == null) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "L'ordine associato è obbligatorio");
	    }
	    
	    // Imposta lo stato su "Pagato" automaticamente
	    pagamento.setStato("Pagato");
	    
	    // Salva il pagamento nel database e restituisce l'oggetto salvato
	    return pagamentoRepository.save(pagamento);
	}
	
	//metodo per ottenere il token dell'use autenticato
    //Recupera il token Bearer dalla richiesta.
    //Se il token è valido, cerca l'utente corrispondente e lo restituisce.
    private AuthUser getAuthenticatedUser(HttpServletRequest request) {
        // Estrai il token dalla richiesta
        String token = getTokenFromRequest(request);
        
        if (token == null || token.isEmpty()) {
            return null; // Nessun token trovato, quindi l'utente non è autenticato
        }
        
        // Trova l'utente autenticato usando il token
        return findUserByToken(token);
    }

    //Estrae il token Bearer dall'header "Authorization".
    //Se il formato è corretto ("Bearer <TOKEN>"), rimuove "Bearer " e restituisce solo il token.
    private String getTokenFromRequest(HttpServletRequest request) {
        // Estrai l'header "Authorization" dalla richiesta
        String authorizationHeader = request.getHeader("Authorization");

        // Verifica che l'header contenga un token con il prefisso "Bearer"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            // Rimuovi la parte "Bearer " (7 caratteri) per ottenere solo il token
            return authorizationHeader.substring(7);
        }
        return null; // Se il token non è presente o non è nel formato corretto
    }

    //Cerca nel database un utente che abbia il token corrispondente.
    //Se lo trova, lo restituisce. Altrimenti, ritorna null.
    private AuthUser findUserByToken(String token) {
        List<AuthUser> users = authRepo.findAll(); // Recupera tutti gli utenti dal repository
        for (AuthUser u : users) {
            if (u.getToken() != null && u.getToken().equals(token)) {
                return u; // Restituisce l'utente se il token corrisponde
            }
        }
        return null; // Se nessun utente ha quel token, ritorna null
    }
}
