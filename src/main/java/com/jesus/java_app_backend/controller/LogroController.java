package com.jesus.java_app_backend.controller;


import com.jesus.java_app_backend.dto.response.LogroResponse;
import com.jesus.java_app_backend.security.UserPrincipal;
import com.jesus.java_app_backend.service.LogroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logros")
@RequiredArgsConstructor
public class LogroController {
    private final LogroService logroService;

    @GetMapping
    public ResponseEntity<List<LogroResponse>> listarTodos(@AuthenticationPrincipal UserPrincipal principal){
        return ResponseEntity.ok(logroService.listarTodos(principal.getId()));
    }

    @PostMapping("/{id}/reclamar")
    public ResponseEntity<LogroResponse> reclamar(
            @PathVariable Integer id,
            @AuthenticationPrincipal UserPrincipal principal
    ) {
        return ResponseEntity.ok(logroService.reclamar(id, principal.getId()));
    }
}
