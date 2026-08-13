package com.jesus.java_app_backend.service.impl;


import com.jesus.java_app_backend.dto.response.UsuarioResponse;
import com.jesus.java_app_backend.entity.Usuario;
import com.jesus.java_app_backend.exception.ResourceNotFoundException;
import com.jesus.java_app_backend.repository.UsuarioRepository;
import com.jesus.java_app_backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse obtenerPerfil(UUID usuarioID){
        Usuario usuario = usuarioRepository.findById(usuarioID)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado"));

        return UsuarioResponse.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .temaPreferido(usuario.getTemaPreferido())
                .fechaRegistro(usuario.getFechaRegistro())
                .build();
    }
}
