package com.hilquiascamelo.aggregate_api.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "relatorio")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @OneToMany(mappedBy = "relatorio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lancamento> lancamentos;

    @Column(nullable = false) // Não pode ser nulo
    private BigDecimal saldo;
}
