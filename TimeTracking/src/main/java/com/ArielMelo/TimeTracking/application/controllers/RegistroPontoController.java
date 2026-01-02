package com.ArielMelo.TimeTracking.application.controllers;

import com.ArielMelo.TimeTracking.application.dtos.RegistroPontoDTO;
import com.ArielMelo.TimeTracking.domain.services.RegistroPontoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pontos")
@RequiredArgsConstructor
public class RegistroPontoController {

    private final RegistroPontoService registroService;

    @PostMapping("/bater/{funcionarioId}")
    public ResponseEntity<RegistroPontoDTO> baterPonto(
            @PathVariable Long funcionarioId,
            @RequestParam String tipo // ENTRADA ou SAIDA
    ) {
        return ResponseEntity.ok(registroService.baterPonto(funcionarioId, tipo));
    }

    @GetMapping("/funcionario/{funcionarioId}")
    public ResponseEntity<List<RegistroPontoDTO>> listarPorFuncionario(@PathVariable Long funcionarioId) {
        return ResponseEntity.ok(registroService.listarPorFuncionario(funcionarioId));
    }
}
