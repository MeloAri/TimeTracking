package com.ArielMelo.TimeTracking.domain.services;

import com.ArielMelo.TimeTracking.application.dtos.RegistroPontoDTO;
import com.ArielMelo.TimeTracking.domain.entities.Funcionario;
import com.ArielMelo.TimeTracking.domain.entities.RegistroPonto;
import com.ArielMelo.TimeTracking.domain.enums.TipoRegistro;
import com.ArielMelo.TimeTracking.domain.repositories.FuncionarioRepository;
import com.ArielMelo.TimeTracking.domain.repositories.RegistroPontoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegistroPontoService {


    private final RegistroPontoRepository registroRepository;
    private final FuncionarioRepository funcionarioRepository;


    public RegistroPontoDTO baterPonto(Long funcionarioId, String tipo) {
        Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));


        RegistroPonto registro = RegistroPonto.builder()
                .funcionario(funcionario)
                .tipo(TipoRegistro.valueOf(tipo))
                .build();


        registroRepository.save(registro);


        return RegistroPontoDTO.builder()
                .id(registro.getId())
                .funcionarioId(funcionarioId)
                .tipo(String.valueOf(registro.getTipo()))
                .horario(registro.getHorario())
                .build();
    }


    public List<RegistroPontoDTO> listarPorFuncionario(Long funcionarioId) {
        return registroRepository.findByFuncionario_Id(funcionarioId)
                .stream()
                .map(r -> RegistroPontoDTO.builder()
                        .id(r.getId())
                        .funcionarioId(r.getFuncionario().getId())
                        .tipo(String.valueOf(r.getTipo()))
                        .horario(r.getHorario())
                        .build())
                .collect(Collectors.toList());
    }
}
