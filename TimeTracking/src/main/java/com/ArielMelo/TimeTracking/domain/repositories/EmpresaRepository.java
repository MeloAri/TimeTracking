package com.ArielMelo.TimeTracking.domain.repositories;

import com.ArielMelo.TimeTracking.domain.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    Optional<Empresa> findByCnpj(String cnpj);
    Optional<Empresa> findByEmailContato(String emailContato);
}
