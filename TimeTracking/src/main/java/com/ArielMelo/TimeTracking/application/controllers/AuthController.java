package com.ArielMelo.TimeTracking.application.controllers;

import com.ArielMelo.TimeTracking.application.dtos.AuthRequestDTO;
import com.ArielMelo.TimeTracking.application.dtos.AuthResponseDTO;
import com.ArielMelo.TimeTracking.application.dtos.RegistroRequestDTO;
import com.ArielMelo.TimeTracking.application.dtos.RegisterAdminDTO;
import com.ArielMelo.TimeTracking.domain.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200") // 🔥 evita problemas de CORS no Angular
public class AuthController {

    private final AuthService authService;

    // ===================== LOGIN =====================
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(
            @RequestBody AuthRequestDTO request
    ) {
        AuthResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    // ===================== REGISTER USER =====================
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(
            @RequestBody RegistroRequestDTO request
    ) {
        AuthResponseDTO response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ===================== REGISTER ADMIN =====================
    @PostMapping("/register-admin")
    public ResponseEntity<AuthResponseDTO> registerAdmin(
            @RequestBody RegisterAdminDTO request
    ) {
        AuthResponseDTO response = authService.registerAdmin(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
