package com.example.arcadeServer.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Utente
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	private String cognome;
	
	private String ruolo;
	
	@OneToMany(mappedBy="utente")
	@JsonManagedReference(value = "user-services")
	private List<Servizio> servizi;
	
	@OneToMany(mappedBy = "utente")
	@JsonManagedReference(value = "user-ordini")
    private List<Ordine> ordini;

    @OneToMany(mappedBy = "utente")
    @JsonManagedReference(value = "user-recensioni")
    private List<Recensione> recensioni;
    
    private String email;
    
    private String password;
    
    public Utente(String nome, String cognome,String ruolo, String email, String password)
	{
		this.nome = nome;
		this.cognome=cognome;
		this.ruolo=ruolo;
		this.email = email;
		this.password = password;
	}
    
    public Utente() {}
	
	public Long getId() {
        return id;
    }

    
    public void setId(Long id) {
        this.id = id;
    }

    
    public String getNome() {
        return nome;
    }

   
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getRuolo() {
    	return ruolo;
    }
    public void setRuolo(String ruolo) {
    	this.ruolo=ruolo;
    }
    
    public String  getEmail() {
    	return email;
    }
    
    public void setEmail(String email) {
    	this.email=email;
    }
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password=password;
    }

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public List<Servizio> getServizi() {
		return servizi;
	}

	public void setServizi(List<Servizio> servizi) {
		this.servizi = servizi;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}
    
    

}

