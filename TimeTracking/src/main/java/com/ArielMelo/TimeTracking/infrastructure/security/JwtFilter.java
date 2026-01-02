package com.ArielMelo.TimeTracking.infrastructure.security;

import com.ArielMelo.TimeTracking.domain.services.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        // Log para debug no console do IntelliJ
        if (request.getRequestURI().startsWith("/api/empresas")) {
            System.out.println("DEBUG: Acessando Empresas. Header presente: " + (authHeader != null));
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);

        try {
            Claims claims = jwtService.extractAllClaims(token);
            String email = claims.getSubject();
            String role = claims.get("role", String.class); // Pega a role do Claim

            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Normaliza a role (Ex: ADMIN)
                var authorities = List.of(new SimpleGrantedAuthority(role.toUpperCase()));

                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        email, null, authorities);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
                System.out.println("✅ Usuario autenticado: " + email + " com Role: " + role);
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao validar token: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}