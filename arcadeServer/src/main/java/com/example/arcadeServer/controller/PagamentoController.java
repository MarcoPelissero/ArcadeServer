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
	public ResponseEntity<?> addPagamento(@RequestBody Pagamento pagamento, HttpServletRequest request) {
	    // Verifica autenticazione utente
	    if (getAuthenticatedUser(request) == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non autorizzato");
	    }

	    // Controlli sui campi obbligatori
	    if (pagamento.getMetodo() == null || pagamento.getMetodo().isEmpty() ||
	        pagamento.getImporto() <= 0 ||
	        pagamento.getOrdine() == null || pagamento.getOrdine().getId() == null) {
	        return ResponseEntity.badRequest().body("Dati pagamento non validi");
	    }
	    //setto lo stato di pagamento su Pagatamento eseguito
	    pagamento.setStato("Pagamento eseguito.");
	    
	    // Salvataggio e risposta
	    return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoRepository.save(pagamento));
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
