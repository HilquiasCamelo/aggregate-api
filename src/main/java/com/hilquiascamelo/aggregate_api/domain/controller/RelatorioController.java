package com.hilquiascamelo.aggregate_api.domain.controller;

import com.hilquiascamelo.aggregate_api.application.exception.ResourceNotFoundException;
import com.hilquiascamelo.aggregate_api.domain.dto.RelatorioDto;
import com.hilquiascamelo.aggregate_api.application.service.RelatorioService;
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

@RequestMapping("/api/relatorio")
@RestController

@Tag(name = "Relatório Management", description = "API for creating, retrieving, updating, and deleting reports.")
public class RelatorioController {

    private static final Logger log
            =  LogManager. getLogger(RelatorioController.class);

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @PostMapping
    public ResponseEntity<RelatorioDto> save(@RequestBody @Validated RelatorioDto relatorioDto) {
        RelatorioDto savedDto = relatorioService.save(relatorioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioDto> findById(@PathVariable("id") Long id) {
        RelatorioDto relatorio = relatorioService.findById(id);
        return ResponseEntity.ok(relatorio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        Optional.ofNullable(relatorioService.findById(id)).orElseThrow(() -> {
            log.error("Unable to delete non-existent data!");
            return new ResourceNotFoundException();
        });
        relatorioService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/page-query")
    public ResponseEntity<Page<RelatorioDto>> pageQuery(RelatorioDto relatorioDto, @PageableDefault(sort = "createAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<RelatorioDto> relatorioPage = relatorioService.findByCondition(relatorioDto, pageable);
        return ResponseEntity.ok(relatorioPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Validated RelatorioDto relatorioDto, @PathVariable("id") Long id) {
        relatorioService.update(relatorioDto, id);
        return ResponseEntity.ok().build();
    }
}
