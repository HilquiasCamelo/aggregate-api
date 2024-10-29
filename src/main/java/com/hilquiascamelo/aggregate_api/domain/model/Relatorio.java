package com.hilquiascamelo.aggregate_api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Relatorio {
    private LocalDate data;
    private List<Lancamento> lancamentos;
    private BigDecimal saldo;
}
