package com.jesus.java_app_backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trivias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trivia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String pregunta;

    private String categoria;
    private  String dificultad;
}
