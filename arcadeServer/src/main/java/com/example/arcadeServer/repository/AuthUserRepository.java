package com.example.arcadeServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.arcadeServer.model.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> 
{
	Optional<AuthUser> findByEmail(String email);
	Optional<AuthUser> findByToken(String token);
}
