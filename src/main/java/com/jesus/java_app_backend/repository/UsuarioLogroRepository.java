package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.UsuarioLogro;
import com.jesus.java_app_backend.entity.UsuarioLogroId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface UsuarioLogroRepository extends JpaRepository<UsuarioLogro, UsuarioLogroId> {
    List<UsuarioLogro> findByUsuarioId(UUID usuarioId);
}
