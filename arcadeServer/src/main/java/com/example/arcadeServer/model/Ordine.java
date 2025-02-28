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
    
    private String titolo_servizio;
    
    private double prezzo;
    
    private String data;
    
    private String nome_utente;
    
    private String email_utente;
    
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
    
    public Ordine(String stato, String titolo_servizio, double prezzo, String data, String nome_utente, String email_utente, Utente utente, Servizio servizio, Pagamento pagamento) {
    	this.stato = stato;
    	this.titolo_servizio = titolo_servizio;
    	this.prezzo = prezzo;
    	this.data = data;
    	this.nome_utente = nome_utente;
    	this.email_utente = email_utente;
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

	public String getTitolo_servizio() {
		return titolo_servizio;
	}

	public void setTitolo_servizio(String titolo_servizio) {
		this.titolo_servizio = titolo_servizio;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	

	public String getNome_utente() {
		return nome_utente;
	}

	public void setNome_utente(String nome_utente) {
		this.nome_utente = nome_utente;
	}

	public String getEmail_utente() {
		return email_utente;
	}

	public void setEmail_utente(String email_utente) {
		this.email_utente = email_utente;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Servizio getServizio() {
		return servizio;
	}

	public void setServizio(Servizio servizio) {
		this.servizio = servizio;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}
    
    
}
