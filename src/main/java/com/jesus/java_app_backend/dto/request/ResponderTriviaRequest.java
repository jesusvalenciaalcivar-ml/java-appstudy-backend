package com.jesus.java_app_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponderTriviaRequest {
    @NotNull(message = "Debes seleccionar una opcion")
    private Integer opcionId;
}
