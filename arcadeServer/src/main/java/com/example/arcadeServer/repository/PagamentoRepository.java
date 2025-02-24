package com.example.arcadeServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.arcadeServer.model.Pagamento;


public interface PagamentoRepository extends JpaRepository<Pagamento, Long>
{
	Optional<Pagamento> findByTransactionId(String transactionId);

}
