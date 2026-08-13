package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.IntentoEjercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IntentoEjercicioRepository extends JpaRepository<IntentoEjercicio, UUID> {
    List<IntentoEjercicio> findByUsuarioId(UUID usuarioId);
}
