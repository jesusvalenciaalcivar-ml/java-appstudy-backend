package com.jesus.java_app_backend.controller;

import com.jesus.java_app_backend.dto.request.ConfirmarRecuperacionRequest;
import com.jesus.java_app_backend.dto.request.LoginRequest;
import com.jesus.java_app_backend.dto.request.RegistroRequest;
import com.jesus.java_app_backend.dto.request.SolicitarRecuperacionRequest;
import com.jesus.java_app_backend.dto.response.LoginResponse;
import com.jesus.java_app_backend.dto.response.UsuarioResponse;
import com.jesus.java_app_backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        UsuarioResponse response = authService.registrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/recuperar/solicitar")
    public ResponseEntity<Void> solicitarRecuperacion(@Valid @RequestBody SolicitarRecuperacionRequest request) {
        authService.solicitarRecuperacion(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/recuperar/confirmar")
    public ResponseEntity<Void> confirmarRecuperacion(@Valid @RequestBody ConfirmarRecuperacionRequest request) {
        authService.confirmarRecuperacion(request.getEmail(), request.getCodigo(), request.getNuevaPassword());
        return ResponseEntity.ok().build();
    }

}