package com.jesus.java_app_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "logros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Logro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String icono;

    @Column(nullable = false, unique = true)
    private String tipo;

    private String stack;
}