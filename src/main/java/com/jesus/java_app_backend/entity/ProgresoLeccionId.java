package com.jesus.java_app_backend.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProgresoLeccionId implements Serializable {

    private UUID usuarioId;
    private Integer leccionId;
}
