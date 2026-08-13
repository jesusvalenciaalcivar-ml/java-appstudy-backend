package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.IntentoTrivia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IntentoTriviaRepository extends JpaRepository<IntentoTrivia, UUID> {
    List<IntentoTrivia> findByUsuarioId(UUID usuarioId);
}
