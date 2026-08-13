package com.jesus.java_app_backend.service;


import com.jesus.java_app_backend.dto.response.LogroResponse;

import java.util.List;
import java.util.UUID;

public interface LogroService {
    List<LogroResponse> listarTodos(UUID usuarioId);
    LogroResponse reclamar(Integer logroId, UUID usuarioId);
}
