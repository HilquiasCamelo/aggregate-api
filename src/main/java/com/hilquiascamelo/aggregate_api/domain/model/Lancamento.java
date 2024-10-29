package com.hilquiascamelo.aggregate_api.domain.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;
    private TipoLancamento tipo;
}
