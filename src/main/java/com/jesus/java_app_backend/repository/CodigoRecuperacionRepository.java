package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.CodigoRecuperacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CodigoRecuperacionRepository extends JpaRepository<CodigoRecuperacion, Integer> {

    Optional<CodigoRecuperacion> findTopByUsuario_IdAndCodigoAndUsadoFalseOrderByFechaCreacionDesc(
            UUID usuarioId, String codigo
    );
}