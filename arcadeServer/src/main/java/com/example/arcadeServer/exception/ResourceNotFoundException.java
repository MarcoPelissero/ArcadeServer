package com.example.arcadeServer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException
{
	// Costruttore che accetta un messaggio descrittivo dell'errore
    public ResourceNotFoundException(String message) {
        // Chiama il costruttore della superclasse RuntimeException passando il messaggio d'errore
        super(message);
    }

}
