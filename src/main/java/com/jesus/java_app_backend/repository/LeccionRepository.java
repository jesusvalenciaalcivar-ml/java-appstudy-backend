package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.Leccion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeccionRepository extends JpaRepository<Leccion, Integer> {

    List<Leccion> findByModuloIdOrderByOrdenAsc(Integer moduloId);

}
