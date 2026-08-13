package com.jesus.java_app_backend.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioLogroId implements Serializable {

    private UUID usuarioId;
    private Integer logroId;
}