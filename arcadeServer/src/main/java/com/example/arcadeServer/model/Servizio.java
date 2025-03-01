package com.example.arcadeServer.model;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Servizio {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
    private String nome; // Nome come chiave primaria

    @NotNull
    private String descrizione;

    @Min(value = 0, message = "Il prezzo deve essere maggiore o uguale a zero")
    @NotNull
    private double prezzo;

    @NotNull
    private String categoria;

    @OneToMany(mappedBy = "servizio")
    @JsonManagedReference(value = "servizio-ordini")
    private List<Ordine> ordini;
    
//    @OneToMany(mappedBy = "servizio")
//    @JsonBackReference(value = "servizio-ordini")
//    private List<Ordine> ordini;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonBackReference(value = "user-services")
    private Utente utente;

    @OneToMany(mappedBy = "servizio")
    @JsonManagedReference(value = "servizio-recensioni")
    private List <Recensione> recensioni;
    
    
    public Servizio (String nome, String descrizione, Double prezzo, String categoria, Utente utente) {
    	this.nome = nome;
    	this.descrizione = descrizione;
    	this.prezzo = prezzo;
    	this.categoria = categoria;
    	this.utente = utente;

    }
    
    public Servizio() {}
    


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// Getter e Setter per nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter per descrizione
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // Getter e Setter per prezzo
    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }


	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}
}