package com.ArielMelo.TimeTracking.application.controllers;

import com.ArielMelo.TimeTracking.application.dtos.FuncionarioDTO;
import com.ArielMelo.TimeTracking.domain.services.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Permite que o Angular acesse a API
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    // NOVO MÉTODO: O Angular chama este método via .getAll()
    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listarTodos() {
        // Certifique-se de que este método existe no seu FuncionarioService
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> criar(@RequestBody FuncionarioDTO dto) {
        return ResponseEntity.ok(funcionarioService.criar(dto));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<FuncionarioDTO>> listarPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(funcionarioService.listarPorEmpresa(empresaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(funcionarioService.buscarPorId(id));
    }

    // Adicione o Delete para o botão de excluir do Angular funcionar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}