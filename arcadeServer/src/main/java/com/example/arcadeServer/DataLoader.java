package com.example.arcadeServer;

import java.sql.Date;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.arcadeServer.model.Ordine;
import com.example.arcadeServer.model.Utente;
import com.example.arcadeServer.repository.OrdineRepository;

import com.example.arcadeServer.repository.UtenteRepository;

@Component
public class DataLoader implements CommandLineRunner
{
    @Autowired
	private UtenteRepository userRepository;

    @Autowired
    private OrdineRepository ordineRepository;
    
    


	/**
	 * Metodo eseguito al termine dell'avvio dell'applicazione. Se il database è vuoto, inserisce alcuni utenti di esempio.
	 */
	@Override
	public void run(String... args) throws Exception
	{
		if (userRepository.count() == 0)
		{
			Utente adminUser = new Utente("nome1", 
					"cognome1",
					"freelance",
					"mario.rossi@example.com", 
					"password1" 
			);
			userRepository.save(adminUser);
			
			Utente user2 = new Utente("nome2", 
					"cognome2",
					"freelance",
					"mario.rossi2@example.com", 
					"password2"
			);
			userRepository.save(user2);

			Utente user3 = new Utente("nome3", 
					"cognome3",
					"cliente",
					"mario.rossi1@example.com", 
					"password1" 
			);
			userRepository.save(user3);

		
			
		
		}
	}
}
