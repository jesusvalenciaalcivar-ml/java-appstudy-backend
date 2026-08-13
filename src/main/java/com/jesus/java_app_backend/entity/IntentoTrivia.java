package com.jesus.java_app_backend.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "intentos_trivia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntentoTrivia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trivia_id", nullable = false)
    private Trivia trivia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "opcion_id", nullable = false)
    private OpcionTrivia opcion;

    @Column(name = "es_correcta", nullable = false)
    private Boolean esCorrecta;

    @Column(nullable = false, updatable = false)
    private OffsetDateTime fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = OffsetDateTime.now();
    }


}
