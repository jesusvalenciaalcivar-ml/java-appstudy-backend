package com.jesus.java_app_backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "terminos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Termino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String palabra;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String definicion;

    private String categoria;

}

