package com.ArielMelo.TimeTracking.application.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String token;
    private Long usuarioId;
}
