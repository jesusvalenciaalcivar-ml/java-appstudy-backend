package com.jesus.java_app_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class TriviaResponse {

    private Integer id;
    private String pregunta;
    private String categoria;
    private String dificultad;
    private List<OpcionTriviaResponse> opciones;
}