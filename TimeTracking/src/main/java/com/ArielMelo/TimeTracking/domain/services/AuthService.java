package com.ArielMelo.TimeTracking.domain.services;

import com.ArielMelo.TimeTracking.application.dtos.AuthRequestDTO;
import com.ArielMelo.TimeTracking.application.dtos.AuthResponseDTO;
import com.ArielMelo.TimeTracking.application.dtos.RegistroRequestDTO;
import com.ArielMelo.TimeTracking.application.dtos.RegisterAdminDTO;
import com.ArielMelo.TimeTracking.domain.entities.Usuario;
import com.ArielMelo.TimeTracking.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    // ===================== LOGIN =====================
    public AuthResponseDTO login(AuthRequestDTO request) {

        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getSenha()
                )
        );

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // 🔥 CORREÇÃO AQUI
        String token = jwtService.generateToken(
                usuario.getEmail(),
                usuario.getRole()
        );

        return AuthResponseDTO.builder()
                .token(token)
                .usuarioId(usuario.getId())
                .build();
    }

    // ===================== REGISTER USER =====================
    public AuthResponseDTO register(RegistroRequestDTO request) {

        Usuario usuario = Usuario.builder()
                .nome(request.getNome())
                .email(request.getEmail())
                .senha(passwordEncoder.encode(request.getSenha()))
                .role("USER")
                .criadoEm(LocalDateTime.now())
                .build();

        usuarioRepository.save(usuario);

        // 🔥 CORREÇÃO AQUI
        String token = jwtService.generateToken(
                usuario.getEmail(),
                usuario.getRole()
        );

        return AuthResponseDTO.builder()
                .token(token)
                .usuarioId(usuario.getId())
                .build();
    }

    // ===================== REGISTER ADMIN =====================
    public AuthResponseDTO registerAdmin(RegisterAdminDTO dto) {

        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já está em uso.");
        }

        Usuario admin = Usuario.builder()
                .nome(dto.getNome())
                .email(dto.getEmail())
                .senha(passwordEncoder.encode(dto.getSenha()))
                .role("ADMIN") // 🔥 FUNDAMENTAL
                .criadoEm(LocalDateTime.now())
                .build();

        usuarioRepository.save(admin);

        // 🔥 CORREÇÃO AQUI
        String token = jwtService.generateToken(
                admin.getEmail(),
                admin.getRole()
        );

        return AuthResponseDTO.builder()
                .token(token)
                .usuarioId(admin.getId())
                .build();
    }
}
