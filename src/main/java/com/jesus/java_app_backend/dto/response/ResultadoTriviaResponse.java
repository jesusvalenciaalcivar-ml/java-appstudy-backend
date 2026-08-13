package com.jesus.java_app_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
public class ResultadoTriviaResponse {
    private  Boolean esCorrecta;
    private Integer opcionCorrectaId;
    private String mensaje;
}
