package com.jesus.java_app_backend.controller;


import com.jesus.java_app_backend.dto.response.UsuarioResponse;
import com.jesus.java_app_backend.security.UserPrincipal;
import com.jesus.java_app_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {
    private final UsuarioService usuarioService;

    @GetMapping("/perfil")
    public ResponseEntity<UsuarioResponse> obtenerPerfil(@AuthenticationPrincipal UserPrincipal principal){
        UsuarioResponse response = usuarioService.obtenerPerfil(principal.getId());
        return ResponseEntity.ok(response);
    }
}
