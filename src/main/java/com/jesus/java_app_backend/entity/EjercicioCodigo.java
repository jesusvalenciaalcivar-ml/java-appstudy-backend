package com.jesus.java_app_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ejercicios_codigo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EjercicioCodigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "leccion_id")
    private Leccion leccion;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String enunciado;

    @Column(name = "respuesta_esperada", nullable = false, columnDefinition = "TEXT")
    private String respuestaEsperada;

    private String dificultad;
}
