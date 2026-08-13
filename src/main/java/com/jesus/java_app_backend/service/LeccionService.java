package com.jesus.java_app_backend.service;

import com.jesus.java_app_backend.dto.response.LeccionResponse;
import com.jesus.java_app_backend.dto.response.ProgresoModuloResponse;

import java.util.List;
import java.util.UUID;

public interface LeccionService {
    List<LeccionResponse> listarPorModulo(Integer moduloId, UUID usuarioId);

    void marcarCompletada(Integer leccionId, UUID usuarioId);
    void desmarcarCompletada(Integer leccionId, UUID usuarioId);

    ProgresoModuloResponse obtenerProgreso(Integer moduloId, UUID usuarioId);
}
