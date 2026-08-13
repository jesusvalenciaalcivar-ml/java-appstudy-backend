package com.jesus.java_app_backend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfirmarRecuperacionRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String codigo;

    @NotBlank
    @Size(min = 8, message = "La contrasena debe tener al menos 8 caracteres")
    private String nuevaPassword;
}