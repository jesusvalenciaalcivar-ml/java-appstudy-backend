package com.jesus.java_app_backend.controller;


import com.jesus.java_app_backend.dto.response.TerminoResponse;
import com.jesus.java_app_backend.service.TerminoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/terminos")
@RequiredArgsConstructor
public class TerminoController {
    private final TerminoService terminoService;

    @GetMapping
    public ResponseEntity<List<TerminoResponse>> listarTodos(){
        return ResponseEntity.ok(terminoService.listarTodos());
    }
}
