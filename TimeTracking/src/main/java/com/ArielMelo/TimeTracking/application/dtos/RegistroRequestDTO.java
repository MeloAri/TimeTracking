package com.ArielMelo.TimeTracking.application.dtos;

import lombok.Data;

@Data
public class RegistroRequestDTO {
    private String nome;
    private String email;
    private String senha;
}
