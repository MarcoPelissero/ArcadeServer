package com.example.arcadeServer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Recensione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commento;
    private int voto;
    
    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonBackReference
    private Utente utente;
    
    public Recensione(String commento, int voto, Utente utente) {
    	this.commento = commento;
    	this.voto = voto;
    	this.utente = utente;
    }
    
    public Recensione() {}
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
        
    public String getCommento() {
    	return commento;
    }
    public void setCommento(String commento) {
    	this.commento=commento;
    }
    public int getVoto() {
    	return voto;
    }
    public void setVoto(int voto) {
    	this.voto=voto;
    }

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
    
    
}
