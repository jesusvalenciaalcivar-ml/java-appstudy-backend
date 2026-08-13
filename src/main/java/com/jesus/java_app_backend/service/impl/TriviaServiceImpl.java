package com.jesus.java_app_backend.service.impl;

import com.jesus.java_app_backend.dto.response.OpcionTriviaResponse;
import com.jesus.java_app_backend.dto.response.PuntuacionResponse;
import com.jesus.java_app_backend.dto.response.ResultadoTriviaResponse;
import com.jesus.java_app_backend.dto.response.TriviaResponse;
import com.jesus.java_app_backend.entity.IntentoTrivia;
import com.jesus.java_app_backend.entity.OpcionTrivia;
import com.jesus.java_app_backend.entity.Trivia;
import com.jesus.java_app_backend.entity.Usuario;
import com.jesus.java_app_backend.exception.ResourceNotFoundException;
import com.jesus.java_app_backend.repository.IntentoTriviaRepository;
import com.jesus.java_app_backend.repository.OpcionTriviaRepository;
import com.jesus.java_app_backend.repository.TriviaRepository;
import com.jesus.java_app_backend.repository.UsuarioRepository;
import com.jesus.java_app_backend.service.TriviaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TriviaServiceImpl implements TriviaService {

    private final TriviaRepository triviaRepository;
    private final OpcionTriviaRepository opcionTriviaRepository;
    private final IntentoTriviaRepository intentoTriviaRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<TriviaResponse> listarTrivias() {
        return triviaRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    @Override
    @Transactional
    public ResultadoTriviaResponse responder(UUID usuarioId, Integer triviaId, Integer opcionId) {
        Trivia trivia = triviaRepository.findById(triviaId)
                .orElseThrow(() -> new ResourceNotFoundException("Trivia no encontrada"));

        OpcionTrivia opcionElegida = opcionTriviaRepository.findById(opcionId)
                .orElseThrow(() -> new ResourceNotFoundException("Opcion no encontrada"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        boolean esCorrecta = opcionElegida.getEsCorrecta();

        IntentoTrivia intento = IntentoTrivia.builder()
                .usuario(usuario)
                .trivia(trivia)
                .opcion(opcionElegida)
                .esCorrecta(esCorrecta)
                .build();

        intentoTriviaRepository.save(intento);

        Integer opcionCorrectaId = opcionTriviaRepository.findByTriviaId(triviaId)
                .stream()
                .filter(OpcionTrivia::getEsCorrecta)
                .map(OpcionTrivia::getId)
                .findFirst()
                .orElse(null);

        return ResultadoTriviaResponse.builder()
                .esCorrecta(esCorrecta)
                .opcionCorrectaId(opcionCorrectaId)
                .mensaje(esCorrecta ? "Correcto!" : "Incorrecto, sigue intentando")
                .build();
    }

    @Override
    public PuntuacionResponse obtenerPuntuacion(UUID usuarioId) {
        List<IntentoTrivia> intentos = intentoTriviaRepository.findByUsuarioId(usuarioId);

        long total = intentos.size();
        long correctas = intentos.stream().filter(IntentoTrivia::getEsCorrecta).count();
        double porcentaje = total == 0 ? 0.0 : (correctas * 100.0 / total);

        return PuntuacionResponse.builder()
                .totalRespondidas(total)
                .totalCorrectas(correctas)
                .porcentaje(porcentaje)
                .build();
    }

    private TriviaResponse mapearAResponse(Trivia trivia) {
        List<OpcionTriviaResponse> opciones = opcionTriviaRepository.findByTriviaId(trivia.getId())
                .stream()
                .map(o -> OpcionTriviaResponse.builder()
                        .id(o.getId())
                        .textoOpcion(o.getTextoOpcion())
                        .build())
                .toList();

        return TriviaResponse.builder()
                .id(trivia.getId())
                .pregunta(trivia.getPregunta())
                .categoria(trivia.getCategoria())
                .dificultad(trivia.getDificultad())
                .opciones(opciones)
                .build();
    }
}