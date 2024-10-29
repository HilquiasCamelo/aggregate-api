package com.hilquiascamelo.aggregate_api.domain.repository;

import com.hilquiascamelo.aggregate_api.domain.model.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, Long>, JpaSpecificationExecutor<Relatorio> {
}