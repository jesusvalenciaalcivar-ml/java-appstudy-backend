package com.jesus.java_app_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProgresoModuloResponse {
    private Integer moduloId;
    private String nombreModulo;
    private Long totalLecciones;
    private Long leccionesCompletadas;
    private Double porcentaje;
}
