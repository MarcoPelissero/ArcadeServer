package com.example.arcadeServer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.arcadeServer.Service.PagamentoService;

@RestController
@RequestMapping("/api/webhook")
@CrossOrigin(origins = {})
public class PayPalController {

	/*private final PagamentoService pagamentoService;

    public PayPalController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @GetMapping("/paypal")
    public String completePayPalPayment(@RequestParam("paymentId") String paymentId, 
                                        @RequestParam("PayerID") String payerId) {
        pagamentoService.aggiornaStatoPagamento(paymentId, "COMPLETATO");
        return "Pagamento completato con successo";
    }*/
}