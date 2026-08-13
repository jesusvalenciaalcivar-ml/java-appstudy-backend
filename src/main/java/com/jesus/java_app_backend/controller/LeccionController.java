package com.jesus.java_app_backend.controller;


import com.jesus.java_app_backend.dto.response.LeccionResponse;
import com.jesus.java_app_backend.dto.response.ProgresoModuloResponse;
import com.jesus.java_app_backend.security.UserPrincipal;
import com.jesus.java_app_backend.service.LeccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lecciones")
@RequiredArgsConstructor
public class LeccionController {

    private final LeccionService leccionService;

    @GetMapping
    public ResponseEntity<List<LeccionResponse>> listarPorModulo(@RequestParam Integer moduloId, @AuthenticationPrincipal UserPrincipal principal){
        return ResponseEntity.ok(leccionService.listarPorModulo(moduloId, principal.getId()));
    }

    @PostMapping("/{id}/completar")
    public ResponseEntity<Void> marcarCompletada(@PathVariable Integer id, @AuthenticationPrincipal UserPrincipal principal){
        leccionService.marcarCompletada(id, principal.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/completar")
    public ResponseEntity<Void> desmarcarCompletada(@PathVariable Integer id, @AuthenticationPrincipal UserPrincipal principal){
        leccionService.desmarcarCompletada(id, principal.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/progreso")
    public ResponseEntity<ProgresoModuloResponse> obtenerProgreso(@RequestParam Integer moduloId, @AuthenticationPrincipal UserPrincipal principal){
        return ResponseEntity.ok(leccionService.obtenerProgreso(moduloId, principal.getId()));

    }
}
