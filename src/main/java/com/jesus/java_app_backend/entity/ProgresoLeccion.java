package com.jesus.java_app_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "progreso_lecciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProgresoLeccion {

    @EmbeddedId
    private ProgresoLeccionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId ("usuarioId")
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("leccionId")
    @JoinColumn(name = "leccion_id")
    private Leccion leccion;

    @Column(nullable = false)
    @Builder.Default
    private Boolean completado = false;

    @Column(name = "fecha_completado")
    private OffsetDateTime fechaCompletado;
}
