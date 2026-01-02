package com.ArielMelo.TimeTracking.domain.entities;


import com.ArielMelo.TimeTracking.domain.enums.TipoRegistro;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_ponto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class RegistroPonto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoRegistro tipo;


    @Column(nullable = false)
    private LocalDateTime horario;
}
