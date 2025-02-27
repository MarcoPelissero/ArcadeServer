package com.example.arcadeServer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.model.Pagamento;
import com.example.arcadeServer.repository.PagamentoRepository;

@RestController
@RequestMapping("/pagamenti")
@CrossOrigin(origins = {})
public class PagamentoController
{
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public ResponseEntity<List<Pagamento>> ottieniTuttiPagamenti()
	{
		List<Pagamento> pagamenti = pagamentoRepository.findAll();
		return ResponseEntity.ok(pagamenti);
	}

}
