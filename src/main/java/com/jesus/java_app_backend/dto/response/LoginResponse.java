package com.jesus.java_app_backend.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private UsuarioResponse usuario;
}
