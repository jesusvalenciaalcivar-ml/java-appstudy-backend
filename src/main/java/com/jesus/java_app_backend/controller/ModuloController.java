package com.jesus.java_app_backend.controller;


import com.jesus.java_app_backend.dto.response.ModuloResponse;
import com.jesus.java_app_backend.service.ModuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ModuloController {

    private final ModuloService moduloService;

    @GetMapping("/api/modulos")
    public ResponseEntity<List<ModuloResponse>> listarPorSack(@RequestParam String stack){
        return ResponseEntity.ok(moduloService.listarPorStack(stack));
    }
}
