package com.jesus.java_app_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TerminoResponse {
    private  Integer id;
    private String palabra;
    private String definicion;
    private String categoria;
}
