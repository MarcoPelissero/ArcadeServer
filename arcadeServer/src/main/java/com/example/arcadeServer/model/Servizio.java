package com.example.arcadeServer.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Servizio {

    @Id
    @NotNull
    private String nome; // Nome come chiave primaria

    @NotNull
    private String descrizione;

    @Min(value = 0, message = "Il prezzo deve essere maggiore o uguale a zero")
    @NotNull
    private double prezzo;

    @NotNull
    private String categoria;

    @OneToMany(mappedBy = "servizio")
    private List<Ordine> ordini;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

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

    // Getter e Setter per categoria
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    // Getter e Setter per ordini
    public List<Ordine> getOrdini() {
        return ordini;
    }

    public void setOrdini(List<Ordine> ordini) {
        this.ordini = ordini;
    }

    // Getter e Setter per utente
    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
