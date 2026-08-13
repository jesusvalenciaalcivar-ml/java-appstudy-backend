package com.jesus.java_app_backend.service;

import com.jesus.java_app_backend.dto.response.ModuloResponse;

import java.util.List;

public interface ModuloService {
    List<ModuloResponse> listarPorStack(String stack);
}
