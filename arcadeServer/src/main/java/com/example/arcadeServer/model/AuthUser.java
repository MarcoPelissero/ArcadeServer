package com.example.arcadeServer.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AuthUser
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String token;

	public AuthUser(String email, String token)
	{
		this.email = email;
		this.token = token;
	}

	public static String generateToken(String email)
	{
		// Genera un token univoco usando UUID
		String token = UUID.randomUUID().toString();
		System.out.println("Token generato: " + token); // stringa di debug in console
		return token;
	}

	public AuthUser(String email)
	{
		this.email = email;
	}

	public AuthUser()
	{
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
}
