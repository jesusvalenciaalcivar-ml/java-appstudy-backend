package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.OpcionTrivia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpcionTriviaRepository extends JpaRepository<OpcionTrivia, Integer> {
    List<OpcionTrivia> findByTriviaId(Integer triviaId);
}
