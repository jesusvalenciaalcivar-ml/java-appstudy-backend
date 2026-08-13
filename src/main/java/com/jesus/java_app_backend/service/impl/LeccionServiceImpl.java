package com.jesus.java_app_backend.service.impl;

import com.jesus.java_app_backend.dto.response.LeccionResponse;
import com.jesus.java_app_backend.dto.response.ProgresoModuloResponse;
import com.jesus.java_app_backend.entity.*;
import com.jesus.java_app_backend.exception.ResourceNotFoundException;
import com.jesus.java_app_backend.repository.LeccionRepository;
import com.jesus.java_app_backend.repository.ModuloRepository;
import com.jesus.java_app_backend.repository.ProgresoLeccionRepository;
import com.jesus.java_app_backend.repository.UsuarioRepository;
import com.jesus.java_app_backend.service.LeccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeccionServiceImpl implements LeccionService {

    private final LeccionRepository leccionRepository;
    private final ModuloRepository moduloRepository;
    private final ProgresoLeccionRepository progresoLeccionRepository;
    private final UsuarioRepository usuarioRepository;


    @Override
    public List<LeccionResponse> listarPorModulo(Integer moduloId, UUID usuarioId) {
        List<Leccion> lecciones = leccionRepository.findByModuloIdOrderByOrdenAsc(moduloId);

        Set<Integer> leccionesCompletadas = progresoLeccionRepository.findByUsuarioId(usuarioId)
                .stream()
                .filter(ProgresoLeccion::getCompletado)
                .map(p-> p.getLeccion().getId())
                .collect(Collectors.toSet());

        return lecciones.stream()
                .map(leccion -> LeccionResponse.builder()
                        .id(leccion.getId())
                        .moduloId(moduloId)
                        .titulo(leccion.getTitulo())
                        .contenido(leccion.getContenido())
                        .orden(leccion.getOrden())
                        .completada(leccionesCompletadas.contains(leccion.getId()))
                        .build())
                .toList();
    }
    @Override
    @Transactional
    public void marcarCompletada(Integer leccionId, UUID usuarioId) {
        Leccion leccion = leccionRepository.findById(leccionId)
                .orElseThrow(() -> new ResourceNotFoundException("Leccion no encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        ProgresoLeccionId id = new ProgresoLeccionId(usuarioId, leccionId);

        ProgresoLeccion progreso = progresoLeccionRepository.findById(id)
                .orElse(ProgresoLeccion.builder()
                        .id(id)
                        .usuario(usuario)
                        .leccion(leccion)
                        .build());

        progreso.setCompletado(true);
        progreso.setFechaCompletado(java.time.OffsetDateTime.now());

        progresoLeccionRepository.save(progreso);
    }

    @Override
    @Transactional
    public void desmarcarCompletada(Integer leccionId, UUID usuarioId) {
        ProgresoLeccionId id= new ProgresoLeccionId(usuarioId, leccionId);

        progresoLeccionRepository.findById(id).ifPresent(progreso->{
            progreso.setCompletado(false);
            progreso.setFechaCompletado(null);
            progresoLeccionRepository.save(progreso);
        });
    }

    @Override
    public ProgresoModuloResponse obtenerProgreso(Integer moduloId, UUID usuarioId) {
        Modulo modulo = moduloRepository.findById(moduloId)
                .orElseThrow(() -> new ResourceNotFoundException("Modulo no encontrado"));

        List<Leccion> lecciones = leccionRepository.findByModuloIdOrderByOrdenAsc(moduloId);
        long total = lecciones.size();

        Set<Integer> idsLecciones = lecciones.stream().map(Leccion::getId).collect(Collectors.toSet());

        long completadas = progresoLeccionRepository.findByUsuarioId(usuarioId)
                .stream()
                .filter(ProgresoLeccion::getCompletado)
                .filter(p -> idsLecciones.contains(p.getLeccion().getId()))
                .count();

        double porcentaje = total == 0 ? 0.0 : (completadas * 100.0 / total);

        return ProgresoModuloResponse.builder()
                .moduloId(modulo.getId())
                .nombreModulo(modulo.getNombre())
                .totalLecciones(total)
                .leccionesCompletadas(completadas)
                .porcentaje(porcentaje)
                .build();
    }
}
