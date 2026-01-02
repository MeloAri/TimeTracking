package com.ArielMelo.TimeTracking.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresaDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String endereco; // ⚠️ Essencial para receber o dado do front
    private String emailContato;
    private LocalDateTime criadoEm;
}