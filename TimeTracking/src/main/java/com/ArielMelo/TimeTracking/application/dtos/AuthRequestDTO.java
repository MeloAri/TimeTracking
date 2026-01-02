package com.ArielMelo.TimeTracking.application.dtos;

import lombok.Data;

@Data
public class AuthRequestDTO {
    private String email;
    private String senha;
}
