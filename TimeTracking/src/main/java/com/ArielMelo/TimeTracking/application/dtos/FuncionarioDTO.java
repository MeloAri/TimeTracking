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
public class FuncionarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha; // ⚠️ Campo Adicionado
    private Long empresaId;
    private LocalDateTime criadoEm;
    private String cargo; // ⚠️ Campo Adicionado
}
