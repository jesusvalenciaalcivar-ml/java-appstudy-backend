package com.jesus.java_app_backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "opciones_trivia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpcionTrivia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trivia_id", nullable = false)
    private Trivia trivia;

    @Column(name = "texto_opcion", nullable = false)
    private String textoOpcion;

    @Column(name = "es_correcta", nullable = false)
    @Builder.Default
    private Boolean esCorrecta = false;
}
