package com.example.arcadeServer.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.arcadeServer.model.Ordine;
import com.example.arcadeServer.model.Pagamento;
import com.example.arcadeServer.repository.OrdineRepository;
import com.example.arcadeServer.repository.PagamentoRepository;


import java.util.Optional;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final OrdineRepository ordineRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, OrdineRepository ordineRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.ordineRepository = ordineRepository;
    }

    @Transactional
    public Pagamento registraPagamento(Long ordineId, String metodo, String transactionId, String stato) {
        Ordine ordine = ordineRepository.findById(ordineId)
                .orElseThrow(() -> new RuntimeException("Ordine non trovato"));

        Pagamento pagamento = new Pagamento();
        pagamento.setOrdine(ordine);
        pagamento.setMetodo(metodo);
        pagamento.setStato(stato);
       

        return pagamentoRepository.save(pagamento);
    }

    @Transactional
    public void aggiornaStatoPagamento(String transactionId, String nuovoStato) {
        Optional<Pagamento> pagamentoOpt = pagamentoRepository.findByTransactionId(transactionId);
        if (pagamentoOpt.isPresent()) {
            Pagamento pagamento = pagamentoOpt.get();
            pagamento.setStato(nuovoStato);
            pagamentoRepository.save(pagamento);
        } else {
            throw new RuntimeException("Pagamento non trovato");
        }
    }
}
