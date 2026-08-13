package com.jesus.java_app_backend.service.impl;

import com.jesus.java_app_backend.dto.response.LogroResponse;
import com.jesus.java_app_backend.entity.*;
import com.jesus.java_app_backend.exception.LogroNoDisponibleException;
import com.jesus.java_app_backend.exception.ResourceNotFoundException;
import com.jesus.java_app_backend.repository.*;
import com.jesus.java_app_backend.service.LogroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LogroServiceImpl implements LogroService {

    private final LogroRepository logroRepository;
    private final UsuarioLogroRepository usuarioLogroRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModuloRepository moduloRepository;
    private final LeccionRepository leccionRepository;
    private final ProgresoLeccionRepository progresoLeccionRepository;
    @Override
    public List<LogroResponse> listarTodos(UUID usuarioId) {
        Set<Integer> idsObtenidos = usuarioLogroRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(ul -> ul.getLogro().getId())
                .collect(Collectors.toSet());

        return logroRepository.findAll()
                .stream()
                .map(logro -> LogroResponse.builder()
                        .id(logro.getId())
                        .nombre(logro.getNombre())
                        .descripcion(logro.getDescripcion())
                        .icono(logro.getIcono())
                        .stack(logro.getStack())
                        .obtenido(idsObtenidos.contains(logro.getId()))
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public LogroResponse reclamar(Integer logroId, UUID usuarioId) {
        Logro logro = logroRepository.findById(logroId)
                .orElseThrow(() -> new ResourceNotFoundException("Logro no encontrado"));

        UsuarioLogroId id = new UsuarioLogroId(usuarioId, logroId);

        if (usuarioLogroRepository.existsById(id)) {
            return LogroResponse.builder()
                    .id(logro.getId())
                    .nombre(logro.getNombre())
                    .descripcion(logro.getDescripcion())
                    .icono(logro.getIcono())
                    .stack(logro.getStack())
                    .obtenido(true)
                    .build();
        }

        boolean cumpleRequisito = logro.getStack() != null
                && verificarRoadmapCompleto(usuarioId, logro.getStack());

        if (!cumpleRequisito) {
            throw new LogroNoDisponibleException("Aun no cumples los requisitos para este logro");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        UsuarioLogro usuarioLogro = UsuarioLogro.builder()
                .id(id)
                .usuario(usuario)
                .logro(logro)
                .fechaObtenido(OffsetDateTime.now())
                .build();

        usuarioLogroRepository.save(usuarioLogro);

        return LogroResponse.builder()
                .id(logro.getId())
                .nombre(logro.getNombre())
                .descripcion(logro.getDescripcion())
                .icono(logro.getIcono())
                .stack(logro.getStack())
                .obtenido(true)
                .build();
    }

    private boolean verificarRoadmapCompleto(UUID usuarioId, String stack) {
        List<Modulo> modulos = moduloRepository.findByStackOrderByOrdenAsc(stack);
        if (modulos.isEmpty()) return false;

        Set<Integer> leccionesCompletadasIds = progresoLeccionRepository.findByUsuarioId(usuarioId)
                .stream()
                .filter(ProgresoLeccion::getCompletado)
                .map(p -> p.getLeccion().getId())
                .collect(Collectors.toSet());

        for (Modulo modulo : modulos) {
            List<Leccion> lecciones = leccionRepository.findByModuloIdOrderByOrdenAsc(modulo.getId());
            if (lecciones.isEmpty()) continue;

            boolean todasCompletadas = lecciones.stream()
                    .allMatch(l -> leccionesCompletadasIds.contains(l.getId()));

            if (!todasCompletadas) return false;
        }

        return true;
    }
}