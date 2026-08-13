package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.ProgresoLeccion;
import com.jesus.java_app_backend.entity.ProgresoLeccionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProgresoLeccionRepository extends JpaRepository<ProgresoLeccion, ProgresoLeccionId> {

    List<ProgresoLeccion> findByUsuarioId(UUID usuarioId);

}
