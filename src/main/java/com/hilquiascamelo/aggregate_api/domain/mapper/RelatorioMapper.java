package com.hilquiascamelo.aggregate_api.domain.mapper;

import com.hilquiascamelo.aggregate_api.domain.dto.RelatorioDto;
import com.hilquiascamelo.aggregate_api.domain.model.Relatorio;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RelatorioMapper extends EntityMapper<RelatorioDto, Relatorio> {
}