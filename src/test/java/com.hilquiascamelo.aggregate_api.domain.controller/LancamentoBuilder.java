package com.hilquiascamelo.aggregate_api.domain.controller;

import com.hilquiascamelo.aggregate_api.domain.dto.LancamentoDto;

import java.util.Collections;
import java.util.List;

public class LancamentoBuilder {
    public static List<String> getIds() {
        return Collections.singletonList("1");
    }

    public static LancamentoDto getDto() {
        LancamentoDto dto = new LancamentoDto();
        dto.setId(Long.valueOf("1"));
        return dto;
    }
}
