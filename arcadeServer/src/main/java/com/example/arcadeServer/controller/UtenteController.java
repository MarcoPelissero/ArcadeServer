package com.example.arcadeServer.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.arcadeServer.model.AuthUser;
import com.example.arcadeServer.model.Utente;
import com.example.arcadeServer.repository.AuthUserRepository;
import com.example.arcadeServer.repository.UtenteRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.arcadeServer.exception.ResourceNotFoundException;


//Annotazione che indica che questa classe è un controller REST e gestisce le richieste HTTP
	@RestController
	@RequestMapping("/utenti")
	@CrossOrigin(origins = {})
public class UtenteController
{

	    // Iniezione automatica del repository per interagire con il database
	    @Autowired
	    private UtenteRepository utenteRepository;

	    @Autowired
		private AuthUserRepository authRepo;

	    @GetMapping
	    public List<Utente> getAllUtenti() {
	        return utenteRepository.findAll();
	    }
	    
	    @PostMapping("/addUser")
	    public Utente createUtente(@RequestBody Utente utente) {
	        return utenteRepository.save(utente);
	    }
	    @PostMapping("/getlogged")
	    public Utente getUtenteByEmail(@RequestBody String email) {
	    	
	    	List<Utente> user = utenteRepository.findAll();
			for(Utente u: user) {
				

				String mail = u.getEmail();
				String otherMail = email;
				otherMail = otherMail.substring(0, otherMail.length()-1);
				otherMail = otherMail.substring(1, otherMail.length());

				if(mail.equals(otherMail)) {
					return u;}
			}
			return null;
	    }
	    
	    @GetMapping("/{id}")
	    public Utente getAuthorById(@PathVariable Long id) {
	        return utenteRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
	    }
	    
	    /*@PutMapping("/{id}")
	    public Utente updateUtente(@PathVariable Long id, @RequestBody Utente utenteDetails) {
	    	Utente utente = utenteRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Utente not found"));

	       
	        utente.setNome(utenteDetails.getNome());

	        return utenteRepository.save(utenteDetails);
	    }*/

	    @DeleteMapping("/{id}")
	    public void deleteUtente(@PathVariable Long id) {
	        Utente utente = utenteRepository.findById(id)
	                .orElseThrow(() -> new ResourceNotFoundException("Utente not found"));

	        utenteRepository.delete(utente);
	    }
	    
	    @PutMapping("/updateUser")
	    public Object updateProfile(@RequestBody Map<String, String> updates, HttpServletRequest request, HttpServletResponse response) {
	        // Ottieni l'utente autenticato tramite il token
	        AuthUser authUser = getAuthenticatedUser(request);
	        if (authUser == null) {
	            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	            return Collections.singletonMap("message", "Non autorizzato");
	        }

	        // Trova l'utente nel database tramite la sua email
	        Optional<Utente> userOpt = utenteRepository.findByEmail(authUser.getEmail());
	        if (!userOpt.isPresent()) {
	            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	            return Collections.singletonMap("message", "Utente non trovato");
	        }

	        // Ottieni l'utente
	        Utente user = userOpt.get();

	        // Aggiorna i dati dell'utente con i nuovi valori forniti nel corpo della richiesta
	        if (updates.containsKey("email")) {
	            user.setEmail(updates.get("email"));
	        }
	        if (updates.containsKey("password")) {
	            String newPassword = updates.get("password");
	            user.setPassword(newPassword);
	        }
	        
	        if (updates.containsKey("nome")) {
	            user.setNome(updates.get("nome"));
	        }
	        if (updates.containsKey("cognome")) {
	            user.setCognome(updates.get("cognome"));
	        }


	        // Salva l'utente aggiornato nel database
	        utenteRepository.save(user);

	        return Collections.singletonMap("message", "Profilo aggiornato con successo");
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
