package com.hilquiascamelo.aggregate_api.application.service;

import com.hilquiascamelo.aggregate_api.domain.dto.RelatorioDto;
import com.hilquiascamelo.aggregate_api.domain.mapper.RelatorioMapper;
import com.hilquiascamelo.aggregate_api.domain.model.Relatorio;
import com.hilquiascamelo.aggregate_api.domain.repository.RelatorioRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.hilquiascamelo.aggregate_api.application.exception.ResourceNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional
public class RelatorioService {
    private final RelatorioRepository repository;
    private final RelatorioMapper relatorioMapper;

    public RelatorioService(RelatorioRepository repository, RelatorioMapper relatorioMapper) {
        this.repository = repository;
        this.relatorioMapper = relatorioMapper;
    }

    public RelatorioDto save(RelatorioDto relatorioDto) {
        Relatorio entity = relatorioMapper.toEntity(relatorioDto);
        return relatorioMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public RelatorioDto findById(Long id) {
        return relatorioMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<RelatorioDto> findByCondition(RelatorioDto relatorioDto, Pageable pageable) {
        Page<Relatorio> entityPage = repository.findAll(pageable);
        List<Relatorio> entities = entityPage.getContent();
        return new PageImpl<>(relatorioMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public RelatorioDto update(RelatorioDto relatorioDto, Long id) {
        RelatorioDto data = findById(id);
        Relatorio entity = relatorioMapper.toEntity(relatorioDto);
        BeanUtils.copyProperties(data, entity);
        return save(relatorioMapper.toDto(entity));
    }
}
