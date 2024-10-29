package com.hilquiascamelo.aggregate_api.domain.dto;

import com.hilquiascamelo.aggregate_api.domain.annotation.CheckDate;
import com.hilquiascamelo.aggregate_api.domain.model.Relatorio;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Financial Transactions (Lancamento). This DTO encapsulates details of " +
        "a financial transaction, including its unique identifier, associated report, transaction value, description, " +
        "transaction date, and type. It serves as a means to transfer transaction data within the application.")
public class LancamentoDto extends AbstractDto<Long> {
    private Long id;
    private Relatorio relatorio;
    private BigDecimal valor;
    private String descricao;
    @CheckDate
    private LocalDate data;
    private String tipo;
}
