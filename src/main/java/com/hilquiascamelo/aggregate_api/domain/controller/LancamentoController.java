package com.hilquiascamelo.aggregate_api.domain.controller;

import com.hilquiascamelo.aggregate_api.application.exception.ResourceNotFoundException;
import com.hilquiascamelo.aggregate_api.domain.dto.LancamentoDto;
import com.hilquiascamelo.aggregate_api.application.service.LancamentoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/lancamento")
@RestController
@Tag(name = "Lancamento Management", description = "API for creating, retrieving, updating, and deleting daily balance records.")
public class LancamentoController {

    private static final Logger log
            =  LogManager. getLogger(LancamentoController.class);
    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService) {
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<LancamentoDto> save(@RequestBody @Validated LancamentoDto lancamentoDto) {
        LancamentoDto savedDto =  lancamentoService.save(lancamentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LancamentoDto> findById(@PathVariable("id") Long id) {
        LancamentoDto lancamento = lancamentoService.findById(id);
        return ResponseEntity.ok(lancamento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(lancamentoService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        lancamentoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<LancamentoDto>> pageQuery(LancamentoDto lancamentoDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<LancamentoDto> lancamentoPage = lancamentoService.findByCondition(lancamentoDto, pageable);
        return ResponseEntity.ok(lancamentoPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated LancamentoDto lancamentoDto, @PathVariable("id") Long id) {
        lancamentoService.update(lancamentoDto, id);
        return ResponseEntity.ok().build();
    }
}
