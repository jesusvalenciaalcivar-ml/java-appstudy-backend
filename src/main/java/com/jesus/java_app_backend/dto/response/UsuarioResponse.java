package com.jesus.java_app_backend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UsuarioResponse {
    private UUID id;
    private String email;
    private String nombre;
    private String temaPreferido;
    private OffsetDateTime fechaRegistro;

}
