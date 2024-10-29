package com.hilquiascamelo.aggregate_api.domain.dto;

import com.hilquiascamelo.aggregate_api.domain.annotation.CheckDate;
import com.hilquiascamelo.aggregate_api.domain.model.Lancamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Schema(description = "Data Transfer Object for Daily Balance Report. This DTO represents a daily financial report " +
        "containing the report's unique identifier, the date of the report, a list of financial transactions (lancamentos) " +
        "associated with that date, and the total balance for the day.")
public class RelatorioDto extends AbstractDto<Long> {
    private Long id;
    @CheckDate
    @NotNull
    private LocalDate data;
    private List<Lancamento> lancamentos;
    @NotNull
    private BigDecimal saldo;
}