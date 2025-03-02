package com.example.arcadeServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.arcadeServer.model.Recensione;
import com.example.arcadeServer.model.Servizio;
import com.example.arcadeServer.model.Utente;
import com.example.arcadeServer.repository.OrdineRepository;
import com.example.arcadeServer.repository.RecensioneRepository;
import com.example.arcadeServer.repository.ServizioRepository;
import com.example.arcadeServer.repository.UtenteRepository;

@Component
public class DataLoader implements CommandLineRunner
{
    @Autowired
	private UtenteRepository userRepository;

    @Autowired
    private OrdineRepository ordineRepository;
    
    @Autowired 
    private ServizioRepository servizioRepository;
    
    @Autowired
    private RecensioneRepository recensioneRepository;


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
					"password1" );
			
			Servizio servizio = new Servizio("servizio1", 
					"descrizione servizio 1",
					60.00,
					"Graphic Design",
					adminUser
	
			);;
			Servizio servizioAltro;
			
			
//			Utente adminUser = new Utente("nome1", 
//					"cognome1",
//					"freelance",
//					"mario.rossi@example.com", 
//					"password1" 
//			);
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
			
			
			if (servizioRepository.count() == 0)
			{
				servizio = new Servizio("servizio1", 
						"descrizione servizio 1",
						60.00,
						"Graphic Design",
						adminUser
		
				);
				servizioRepository.save(servizio);
				
				
				servizioAltro = new Servizio("servizio2", 
						"descrizione servizio 2",
						160.00,
						"Web Development",
						adminUser
		
				);
				servizioRepository.save(servizioAltro);
				
				Servizio servizioAltro1 = new Servizio("servizio3", 
						"descrizione servizio 3",
						99.00,
						"Consulting",
						adminUser
		
				);
				servizioRepository.save(servizioAltro1);
				
			}
			
//			if (recensioneRepository.count() == 0)
//			{
//				Recensione recensione = new Recensione("Servizio impeccabile, molto soddisfatto del risultato finale. Consigliatissimo!", 
//						4,
//						user3, 
//						servizio
//		
//				);
//				recensioneRepository.save(recensione);
//				
//				Recensione recensione1 = new Recensione("Molto professionale e veloce, ma avrei preferito una comunicazione un po' più rapida.", 
//						2,
//						user2,
//						servizio
//		
//				);
//				recensioneRepository.save(recensione1);
//				
//				
//				
//				
//			}

		
			
		
		}
	}
	
	
	
}
