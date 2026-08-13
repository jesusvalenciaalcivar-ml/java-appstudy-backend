package com.jesus.java_app_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PuntuacionResponse {
    private Long totalRespondidas;
    private Long totalCorrectas;
    private Double porcentaje;
}
