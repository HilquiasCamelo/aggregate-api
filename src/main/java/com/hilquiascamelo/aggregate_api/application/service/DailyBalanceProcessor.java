package com.hilquiascamelo.aggregate_api.application.service;

import com.hilquiascamelo.aggregate_api.domain.model.Lancamento;
import com.hilquiascamelo.aggregate_api.domain.repository.LancamentoRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DailyBalanceProcessor {

    private final LancamentoRepository lancamentoRepository;

    public DailyBalanceProcessor(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @KafkaListener(topics = "transaction", groupId = "grupo-consumidor")
    public void processDailyBalance(String message) {
        if (isValidMessage(message)) {
            BigDecimal valor = new BigDecimal(message);
            Lancamento lancamento = new Lancamento();
            lancamento.setValor(valor);
            lancamento.setDescricao("Lançamento: " + valor);
            lancamentoRepository.save(lancamento);
            System.out.println("Lançamento processado: " + lancamento);
        } else {
            System.out.println("Mensagem inválida: " + message);
        }
    }

    private boolean isValidMessage(String message) {
        if (message == null || message.trim().isEmpty()) {
            return false;
        }
        try {
            new BigDecimal(message);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
