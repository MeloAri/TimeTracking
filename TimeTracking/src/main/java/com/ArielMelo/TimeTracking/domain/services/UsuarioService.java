package com.ArielMelo.TimeTracking.domain.services;

import com.ArielMelo.TimeTracking.application.dtos.auth.UserRegisterRequest;
import com.ArielMelo.TimeTracking.domain.entities.Usuario;
import com.ArielMelo.TimeTracking.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public void cadastrar(UserRegisterRequest request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setSenha(passwordEncoder.encode(request.getSenha()));
        usuario.setRole("ADMIN"); // ou USER
        usuario.setCriadoEm(LocalDateTime.now());

        usuarioRepository.save(usuario);
    }
}
