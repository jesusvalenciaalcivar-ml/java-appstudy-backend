package com.jesus.java_app_backend.service.impl;

import com.jesus.java_app_backend.dto.response.TerminoResponse;
import com.jesus.java_app_backend.entity.Termino;
import com.jesus.java_app_backend.repository.TerminoRepository;
import com.jesus.java_app_backend.service.TerminoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerminoServiceImpl implements TerminoService {
    private final TerminoRepository terminoRepository;
    @Override
    public List<TerminoResponse> listarTodos(){
        return terminoRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    private TerminoResponse mapearAResponse(Termino termino){
        return TerminoResponse.builder()
                .id(termino.getId())
                .palabra(termino.getPalabra())
                .definicion(termino.getDefinicion())
                .categoria(termino.getCategoria())
                .build();
    }
}
