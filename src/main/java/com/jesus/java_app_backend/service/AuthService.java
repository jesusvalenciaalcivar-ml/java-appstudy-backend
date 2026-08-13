package com.jesus.java_app_backend.service;

import com.jesus.java_app_backend.dto.request.LoginRequest;
import com.jesus.java_app_backend.dto.request.RegistroRequest;
import com.jesus.java_app_backend.dto.response.LoginResponse;
import com.jesus.java_app_backend.dto.response.UsuarioResponse;

public interface AuthService {
    UsuarioResponse registrar(RegistroRequest request);
    LoginResponse login (LoginRequest request);
    void solicitarRecuperacion(String email);

    void confirmarRecuperacion(String email, String codigo, String nuevaPassword);
}
