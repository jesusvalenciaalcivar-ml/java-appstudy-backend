package com.jesus.java_app_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "intentos_ejercicio")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntentoEjercicio{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ejercicio_id", nullable = false)
    private  EjercicioCodigo ejercicio;

    @Column(name = "respuesta_enviada", columnDefinition = "TEXT")
    private String respuestaEnviada;

    @Column(name = "es_correcto", nullable = false)
    @Builder.Default
    private Boolean esCorrecto = false;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime fecha;

    @PrePersist
    protected void onCreate(){
        this.fecha = OffsetDateTime.now();
    }

}
