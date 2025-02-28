package com.example.arcadeServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.arcadeServer.model.Servizio;
import java.util.List;

public interface ServizioRepository extends JpaRepository<Servizio, Long> {

    // Trova Servizi per nome
    List<Servizio> findByNome(String nome);

    // Trova Servizi per categoria
    List<Servizio> findByCategoria(String categoria);

    // Trova Servizi con prezzo maggiore di un certo valore
    List<Servizio> findByPrezzoGreaterThan(double prezzo);
}
