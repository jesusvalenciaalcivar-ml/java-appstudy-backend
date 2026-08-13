package com.jesus.java_app_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.id.IntegralDataTypeHolder;

@Getter
@Builder
@AllArgsConstructor
public class LeccionResponse {
    private Integer id;
    private Integer moduloId;
    private String titulo;
    private String contenido;
    private Integer orden;
    private Boolean completada;
}
