package com.hilquiascamelo.aggregate_api.application.service;

import com.hilquiascamelo.aggregate_api.domain.dto.LancamentoDto;
import com.hilquiascamelo.aggregate_api.domain.mapper.LancamentoMapper;
import com.hilquiascamelo.aggregate_api.domain.model.Lancamento;
import com.hilquiascamelo.aggregate_api.domain.repository.LancamentoRepository;
import com.hilquiascamelo.aggregate_api.application.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
public class LancamentoService {
    private final LancamentoRepository repository;
    private final LancamentoMapper lancamentoMapper;

    public LancamentoService(LancamentoRepository repository, LancamentoMapper lancamentoMapper) {
        this.repository = repository;
        this.lancamentoMapper = lancamentoMapper;
    }

    public LancamentoDto save(LancamentoDto lancamentoDto) {
        Lancamento entity = lancamentoMapper.toEntity(lancamentoDto);
        return lancamentoMapper.toDto(repository.save(entity));
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public LancamentoDto findById(Long id) {
        return lancamentoMapper.toDto(repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new));
    }

    public Page<LancamentoDto> findByCondition(LancamentoDto lancamentoDto, Pageable pageable) {
        Page<Lancamento> entityPage = repository.findAll(pageable);
        List<Lancamento> entities = entityPage.getContent();
        return new PageImpl<>(lancamentoMapper.toDto(entities), pageable, entityPage.getTotalElements());
    }

    public LancamentoDto update(LancamentoDto lancamentoDto, Long id) {
        LancamentoDto data = findById(id);
        Lancamento entity = lancamentoMapper.toEntity(lancamentoDto);
        BeanUtils.copyProperties(data, entity);
        return save(lancamentoMapper.toDto(entity));
    }
}
