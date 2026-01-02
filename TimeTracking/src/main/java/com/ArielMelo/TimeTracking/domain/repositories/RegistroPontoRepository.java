package com.ArielMelo.TimeTracking.domain.repositories;

import com.ArielMelo.TimeTracking.domain.entities.RegistroPonto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Long> {
    List<RegistroPonto> findByFuncionario_Id(Long funcionarioId);
}
