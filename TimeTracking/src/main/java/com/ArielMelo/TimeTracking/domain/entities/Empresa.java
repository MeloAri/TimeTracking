package com.ArielMelo.TimeTracking.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "empresa")
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true) // Agora opcional
    private String cnpj;

    @Column(nullable = false) // Obrigatório
    private String endereco;

    @Column(nullable = true) // Agora opcional
    private String emailContato;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
    private List<Funcionario> funcionarios;

    @PrePersist
    public void prePersist() {
        if (this.criadoEm == null) {
            this.criadoEm = LocalDateTime.now();
        }
    }
}