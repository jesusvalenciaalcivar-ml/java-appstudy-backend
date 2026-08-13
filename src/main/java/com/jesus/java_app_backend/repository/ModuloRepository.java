package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModuloRepository  extends JpaRepository<Modulo, Integer> {
    List<Modulo> findByStackOrderByOrdenAsc(String stack);
}
