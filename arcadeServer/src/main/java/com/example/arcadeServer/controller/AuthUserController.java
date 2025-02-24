package com.example.arcadeServer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.model.AuthUser;
import com.example.arcadeServer.model.Utente;
import com.example.arcadeServer.repository.AuthUserRepository;
import com.example.arcadeServer.repository.UtenteRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {}) // Disabilita richieste CORS da origini esterne
public class AuthUserController
{
	@Autowired
	private AuthUserRepository authRepo;

	@Autowired
	private UtenteRepository userRepository;
	
	/**
	 * 
	 * @param body
	 * @return result: messaggio di errore o di successo
	 * Metodo che gestisce il login di uno user già esistente tramite email e password
	 */
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) // il corpo della HTTP da formato JSON si trasforma in Map 
	{
		Map<String, String> result = new HashMap<String, String>();
		String email = body.get("email"); // Si ottiene il valore dell'email
		String password = body.get("password"); // E della password

		if (email == null || password == null)
		{
			result.put("errore", "Credenziali non valide");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result); // Imposta il body della richiesta come result
		}

		Optional<Utente> optionalUser = userRepository.findByEmail(email); // Cerca se esiste uno user con determinata email
		if (!optionalUser.isPresent() || !optionalUser.get().getPassword().equals(password))
		{
			result.put("errore", "Credenziali non valide");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}
		Utente user = optionalUser.get(); // Se esiste ottiene lo user

		String token = AuthUser.generateToken(email);
		Optional<AuthUser> authUser = authRepo.findByEmail(user.getEmail());

		if (authUser.isPresent()) // Se non è la prima volta che lo user fa l'accesso si cambia il token
		{
			authUser.get().setToken(token);
			authRepo.save(authUser.get());
		} else // Altrimenti si crea un nuovo user autenticato
		{
			AuthUser nuovoUser = new AuthUser(user.getEmail(), token);
			authRepo.save(nuovoUser);
		}

		result.put("messaggio", "Login effettuato con successo");
		result.put("token", token);
		return ResponseEntity.ok(result);
	}
	
	/**
	 * 
	 * @param authHeader valore dell'header HTTP
	 * @return result: messaggio di errore o di successo
	 * Metodo che permette il logout di uno user
	 */
	@PostMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(@RequestHeader("Authorization") String authHeader) // Prende il valore dell’header HTTP con nome "Authorization"
	{
		Map<String, String> result = new HashMap<String, String>();
		if (authHeader == null || authHeader.isEmpty())
		{
			result.put("errore", "Nessun token fornito");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}

		String token;
		if (authHeader != null && authHeader.startsWith("Bearer ")) // Se il token è inviato come "Bearer <token>", estrae la parte dopo "Bearer " e quindi il token
		{
			token = authHeader.substring(7);
		} else
		{
			token = authHeader; // Prende il token anche quando non c'è Bearer
		}
		if (token == null || token.isEmpty())
		{
			result.put("errore", "Token non valido");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}
		Optional<AuthUser> authUserOpt = authRepo.findByToken(token); // Trova lo user tramite token
		if (!authUserOpt.isPresent()) // Se per qualche motivo il token non corrisponde manda un errore
		{
			result.put("errore", "Errore nel logout, token non valido");
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
		}
		AuthUser authUser = authUserOpt.get();
	    authUser.setToken(null); // Rimuove il token (settandolo a null)
	    authRepo.save(authUser);

	    result.put("messaggio", "Logout effettuato con successo");
	    return ResponseEntity.ok(result);
	}
}