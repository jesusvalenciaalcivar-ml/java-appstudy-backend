package com.jesus.java_app_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "usuario_logros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioLogro {

    @EmbeddedId
    private UsuarioLogroId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("logroId")
    @JoinColumn(name = "logro_id")
    private Logro logro;

    @Column(name = "fecha_obtenido", nullable = false)
    private OffsetDateTime fechaObtenido;
}