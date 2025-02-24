package com.example.arcadeServer.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.Service.PagamentoService;
import com.fasterxml.jackson.databind.JsonNode;



@RestController
@RequestMapping("/api/webhook")
public class StripeController {

    private final PagamentoService pagamentoService;

    public StripeController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

   
}
