package com.jesus.java_app_backend.repository;

import com.jesus.java_app_backend.entity.Trivia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TriviaRepository extends JpaRepository<Trivia, Integer> {
}
