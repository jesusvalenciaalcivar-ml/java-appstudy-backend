package com.jesus.java_app_backend.controller;


import com.jesus.java_app_backend.dto.request.ResponderTriviaRequest;
import com.jesus.java_app_backend.dto.response.PuntuacionResponse;
import com.jesus.java_app_backend.dto.response.ResultadoTriviaResponse;
import com.jesus.java_app_backend.dto.response.TriviaResponse;
import com.jesus.java_app_backend.security.UserPrincipal;
import com.jesus.java_app_backend.service.TriviaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trivias")
@RequiredArgsConstructor
public class TriviaController {
     private final TriviaService triviaService;
     @GetMapping
     public ResponseEntity<List<TriviaResponse>> listarTrivias(){
         return ResponseEntity.ok(triviaService.listarTrivias());
     }

     @PostMapping("/{id}/responder")
     public ResponseEntity<ResultadoTriviaResponse> responder(@PathVariable Integer id,
                                                             @Valid @RequestBody ResponderTriviaRequest request,
                                                             @AuthenticationPrincipal UserPrincipal principal){
         ResultadoTriviaResponse resultado = triviaService.responder(principal.getId(), id, request.getOpcionId());
         return  ResponseEntity.ok(resultado);

     }

     @GetMapping("/puntuacion")
     public ResponseEntity<PuntuacionResponse> obtenerPuntuacion(@AuthenticationPrincipal UserPrincipal principal){
         return ResponseEntity.ok(triviaService.obtenerPuntuacion(principal.getId()));
     }

}
