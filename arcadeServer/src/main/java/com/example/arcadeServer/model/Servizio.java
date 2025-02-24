package com.example.arcadeServer.model;

import java.util.List;

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
    
    @NotNull
    private String nome;
    
    @NotNull
    private String descrizione;
    
    @Min(value = 0, message = "Il prezzo deve essere non nullo")
	@NotNull
    private double prezzo;
    
    @NotNull
    private String categoria;

    @OneToMany(mappedBy = "servizio")
    private List<Ordine> ordini;
    
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;
    
    
    public Long getId() {
        return id;
    }

    // Imposta l'ID dell'autore
    public void setId(Long id) {
        this.id = id;
    }

    // Restituisce il nome dell'autore
    public String getNome() {
        return nome;
    }

    // Imposta il nome dell'autore
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescrizione() {
        return descrizione;
    }

    // Imposta l'ID dell'autore
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    // Restituisce il nome dell'autore
    public double getPrezzo() {
        return prezzo;
    }

    // Imposta il nome dell'autore
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
    public String getCategoria() {
    	return categoria;
    }
    public void setCategoria() {
    	this.categoria=categoria;    }
}
