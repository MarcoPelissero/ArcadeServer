package com.example.arcadeServer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String metodo;
    private double importo;
    
    @OneToOne
    @JoinColumn(name = "ordine_id")
    private Ordine ordine;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getMetodo() {
    	return metodo;
    }
    
    public void setMetodo(String metodo) {
    	this.metodo=metodo;
    }
    public double getImporto() {
    	return importo;
    }
    public void setImporto(double importo) {
    	this.importo=importo;
    }
}
