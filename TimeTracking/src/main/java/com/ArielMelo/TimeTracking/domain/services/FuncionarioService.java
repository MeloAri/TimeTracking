package com.ArielMelo.TimeTracking.domain.services;

import com.ArielMelo.TimeTracking.application.dtos.FuncionarioDTO;
import com.ArielMelo.TimeTracking.domain.entities.Empresa;
import com.ArielMelo.TimeTracking.domain.entities.Funcionario;
import com.ArielMelo.TimeTracking.domain.repositories.EmpresaRepository;
import com.ArielMelo.TimeTracking.domain.repositories.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;
    private final EmpresaRepository empresaRepository;

    // ESSENCIAL: Resolve o Erro 500 ao abrir a lista
    public List<FuncionarioDTO> listarTodos() {
        return funcionarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public FuncionarioDTO criar(FuncionarioDTO dto) {
        Empresa empresa = empresaRepository.findById(dto.getEmpresaId())
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        Funcionario funcionario = Funcionario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(dto.getSenha())
                .cargo(dto.getCargo())
                .empresa(empresa)
                .criadoEm(LocalDateTime.now())
                .build();

        return toDTO(funcionarioRepository.save(funcionario));
    }

    @Transactional
    public void excluir(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado");
        }
        funcionarioRepository.deleteById(id);
    }

    public List<FuncionarioDTO> listarPorEmpresa(Long empresaId) {
        return funcionarioRepository.findByEmpresaId(empresaId).stream().map(this::toDTO).toList();
    }

    public FuncionarioDTO buscarPorId(Long id) {
        return funcionarioRepository.findById(id).map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    private FuncionarioDTO toDTO(Funcionario f) {
        return FuncionarioDTO.builder()
                .id(f.getId())
                .nome(f.getNome())
                .email(f.getEmail())
                .cargo(f.getCargo())
                .empresaId(f.getEmpresa() != null ? f.getEmpresa().getId() : null)
                .criadoEm(f.getCriadoEm())
                .build();
    }
}