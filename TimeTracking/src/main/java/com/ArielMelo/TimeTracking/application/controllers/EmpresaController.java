package com.ArielMelo.TimeTracking.application.controllers;

import com.ArielMelo.TimeTracking.application.dtos.EmpresaDTO;
import com.ArielMelo.TimeTracking.domain.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    @PostMapping
    public ResponseEntity<EmpresaDTO> criar(@RequestBody EmpresaDTO dto) {
        return ResponseEntity.ok(empresaService.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> listar() {
        return ResponseEntity.ok(empresaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpresaDTO> atualizar(
            @PathVariable Long id,
            @RequestBody EmpresaDTO dto) {
        return ResponseEntity.ok(empresaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        empresaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}