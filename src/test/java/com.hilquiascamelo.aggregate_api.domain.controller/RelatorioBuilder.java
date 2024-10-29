package com.hilquiascamelo.aggregate_api.domain.controller;

import java.util.Collections;
import java.util.List;

import com.hilquiascamelo.aggregate_api.domain.dto.RelatorioDto;

public class RelatorioBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static RelatorioDto getDto() {
        RelatorioDto dto = new RelatorioDto();
        dto.setId(Long.valueOf("1"));
        return dto;
    }
}
