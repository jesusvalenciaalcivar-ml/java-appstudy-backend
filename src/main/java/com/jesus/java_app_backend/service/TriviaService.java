package com.jesus.java_app_backend.service;

import com.jesus.java_app_backend.dto.response.PuntuacionResponse;
import com.jesus.java_app_backend.dto.response.ResultadoTriviaResponse;
import com.jesus.java_app_backend.dto.response.TriviaResponse;

import java.util.List;
import java.util.UUID;

public interface TriviaService {
    List<TriviaResponse> listarTrivias();

    ResultadoTriviaResponse responder(UUID usuarioId, Integer triviaId, Integer opcionId);

    PuntuacionResponse obtenerPuntuacion(UUID usuarioId);
}
