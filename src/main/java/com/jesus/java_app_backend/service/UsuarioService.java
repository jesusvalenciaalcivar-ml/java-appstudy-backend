package com.jesus.java_app_backend.service;

import com.jesus.java_app_backend.dto.response.UsuarioResponse;

import java.util.UUID;

public interface UsuarioService {
    UsuarioResponse obtenerPerfil(UUID usuarioID);
}
