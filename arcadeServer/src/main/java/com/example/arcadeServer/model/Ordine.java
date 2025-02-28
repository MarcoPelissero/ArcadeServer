package com.example.arcadeServer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String stato;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonBackReference(value = "user-ordini")
    private Utente utente;
    
    @ManyToOne
    @JoinColumn(name = "servizio_id")
    @JsonBackReference(value = "servizio-ordini")
    private Servizio servizio;
    
    @OneToOne(mappedBy = "ordine")
    @JsonBackReference
    private Pagamento pagamento;
    
    public Ordine(String stato, Utente utente, Servizio servizio, Pagamento pagamento) {
    	this.stato = stato;
    	this.utente = utente;
    	this.servizio = servizio;
    	this.pagamento = pagamento;
    }
    
    public Ordine() {}
    
    
    public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }
    public String getStato() {
    	return stato;
    }
    public void setStato(String stato) {
    	this.stato=stato;
    }

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
    
}
