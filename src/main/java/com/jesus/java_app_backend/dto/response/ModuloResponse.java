package com.jesus.java_app_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ModuloResponse {
    private Integer id;
    private String nombre;
    private Integer orden;
    private String descripcion;
}
