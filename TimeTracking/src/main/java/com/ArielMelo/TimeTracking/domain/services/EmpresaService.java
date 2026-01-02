package com.ArielMelo.TimeTracking.domain.services;

import com.ArielMelo.TimeTracking.application.dtos.EmpresaDTO;
import com.ArielMelo.TimeTracking.domain.entities.Empresa;
import com.ArielMelo.TimeTracking.domain.repositories.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    /* ==========================
       CREATE
       ========================== */
    @Transactional
    public EmpresaDTO criar(EmpresaDTO dto) {
        Empresa empresa = Empresa.builder()
                .nome(dto.getNome())
                .cnpj(dto.getCnpj())
                .endereco(dto.getEndereco())
                .emailContato(dto.getEmailContato())
                .criadoEm(LocalDateTime.now())
                .build();

        return toDTO(empresaRepository.save(empresa));
    }

    /* ==========================
       READ - ALL
       ========================== */
    @Transactional(readOnly = true)
    public List<EmpresaDTO> listar() {
        return empresaRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /* ==========================
       READ - BY ID
       ========================== */
    @Transactional(readOnly = true)
    public EmpresaDTO buscarPorId(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Empresa não encontrada com id: " + id)
                );

        return toDTO(empresa);
    }

    /* ==========================
       UPDATE
       ========================== */
    @Transactional
    public EmpresaDTO atualizar(Long id, EmpresaDTO dto) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Empresa não encontrada para atualização: " + id)
                );

        empresa.setNome(dto.getNome());
        empresa.setCnpj(dto.getCnpj());
        empresa.setEndereco(dto.getEndereco());
        empresa.setEmailContato(dto.getEmailContato());

        return toDTO(empresaRepository.save(empresa));
    }

    /* ==========================
       DELETE
       ========================== */
    @Transactional
    public void deletar(Long id) {
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Empresa não encontrada para exclusão: " + id)
                );

        empresaRepository.delete(empresa);
    }

    /* ==========================
       MAPPER
       ========================== */
    private EmpresaDTO toDTO(Empresa entity) {
        return EmpresaDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .cnpj(entity.getCnpj())
                .endereco(entity.getEndereco())
                .emailContato(entity.getEmailContato())
                .criadoEm(entity.getCriadoEm())
                .build();
    }
}
