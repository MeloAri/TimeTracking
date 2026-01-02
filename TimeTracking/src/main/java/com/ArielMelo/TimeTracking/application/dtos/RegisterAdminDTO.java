package com.ArielMelo.TimeTracking.application.dtos;

import lombok.Data;

@Data
public class RegisterAdminDTO {
    private String nome;
    private String email;
    private String senha;
}

