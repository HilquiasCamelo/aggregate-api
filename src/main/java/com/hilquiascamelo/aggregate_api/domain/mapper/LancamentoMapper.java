package com.hilquiascamelo.aggregate_api.domain.mapper;

import com.hilquiascamelo.aggregate_api.domain.dto.LancamentoDto;
import com.hilquiascamelo.aggregate_api.domain.model.Lancamento;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface LancamentoMapper extends EntityMapper<LancamentoDto, Lancamento> {
}