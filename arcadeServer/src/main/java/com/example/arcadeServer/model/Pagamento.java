package com.example.arcadeServer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String metodo;
    
    @Min(value = 0, message = "L'importo deve essere non nullo")
   	@NotNull
    private double importo;
    
    @NotNull
    private String stato;
    
    @Column(unique = true)
    private String transactionId;
    
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
    public String getStato() {
    	return stato;
    }
    public void setStato(String stato) {
    	this.stato=stato;
    }
    public String getTransactionId() {
    	return transactionId;
    }
    public void setTransactionId(String transactionId) {
    	this.transactionId=transactionId;
    }
    public Ordine getOrdine() {
    	return ordine;
    }
    public void setOrdine(Ordine ordine) {
    	this.ordine=ordine;
    }
}
