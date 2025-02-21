package com.example.arcadeServer.model;

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
    private Utente utente;
    
    @ManyToOne
    @JoinColumn(name = "servizio_id")
    private Servizio servizio;
    
    @OneToOne(mappedBy = "ordine")
    private Pagamento pagamento;
    
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
}
