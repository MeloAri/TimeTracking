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
public class RegistroPontoDTO {
    private Long id;
    private Long funcionarioId;
    private String tipo;
    private LocalDateTime horario;
}
