package com.ArielMelo.TimeTracking.domain.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "funcionario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String nome;


    @Column(nullable = false, unique = true)
    private String email;


    @Column(nullable = false)
    private String senha;


    @ManyToOne
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;


    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @Column(nullable = false)
    private String cargo;


    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
    private List<RegistroPonto> registros;
}
