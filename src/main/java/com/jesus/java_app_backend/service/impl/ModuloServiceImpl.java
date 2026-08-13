package com.jesus.java_app_backend.service.impl;

import com.jesus.java_app_backend.dto.response.ModuloResponse;
import com.jesus.java_app_backend.entity.Modulo;
import com.jesus.java_app_backend.repository.ModuloRepository;
import com.jesus.java_app_backend.service.ModuloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModuloServiceImpl implements ModuloService {

    private final ModuloRepository moduloRepository;

    @Override
    public List<ModuloResponse> listarPorStack(String stack){
        return moduloRepository.findByStackOrderByOrdenAsc(stack)
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    private ModuloResponse mapearAResponse(Modulo modulo){
        return ModuloResponse.builder()
                .id(modulo.getId())
                .nombre(modulo.getNombre())
                .orden(modulo.getOrden())
                .descripcion(modulo.getDescripcion())
                .build();
    }
}
