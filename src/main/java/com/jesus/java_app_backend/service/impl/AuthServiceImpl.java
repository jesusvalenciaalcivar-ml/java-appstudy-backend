package com.jesus.java_app_backend.service.impl;

import com.jesus.java_app_backend.dto.request.LoginRequest;
import com.jesus.java_app_backend.dto.request.RegistroRequest;
import com.jesus.java_app_backend.dto.response.LoginResponse;
import com.jesus.java_app_backend.dto.response.UsuarioResponse;
import com.jesus.java_app_backend.entity.CodigoRecuperacion;
import com.jesus.java_app_backend.entity.Usuario;
import com.jesus.java_app_backend.exception.CodigoInvalidoException;
import com.jesus.java_app_backend.exception.CredencialesInvalidasException;
import com.jesus.java_app_backend.exception.EmailYaRegistradoException;

import com.jesus.java_app_backend.repository.CodigoRecuperacionRepository;
import com.jesus.java_app_backend.repository.UsuarioRepository;
import com.jesus.java_app_backend.security.JwtUtil;
import com.jesus.java_app_backend.service.AuthService;
import com.jesus.java_app_backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final CodigoRecuperacionRepository codigoRecuperacionRepository;
    private final EmailService emailService;

    @Override
    public void solicitarRecuperacion(String email) {
        usuarioRepository.findByEmail(email).ifPresent(usuario -> {
            String codigo = String.format("%06d", new SecureRandom().nextInt(1_000_000));

            CodigoRecuperacion registro = CodigoRecuperacion.builder()
                    .usuario(usuario)
                    .codigo(codigo)
                    .fechaExpiracion(OffsetDateTime.now().plusMinutes(15))
                    .build();

            codigoRecuperacionRepository.save(registro);
            emailService.enviarCodigoRecuperacion(email, codigo);
        });
        // Nota: si el email no existe, no hacemos nada, pero NO informamos
        // eso al cliente - evita que alguien pueda usar este endpoint para
        // averiguar que emails estan registrados en el sistema.
    }

    @Override
    @Transactional
    public void confirmarRecuperacion(String email, String codigo, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new CodigoInvalidoException("Codigo invalido o expirado"));

        CodigoRecuperacion registro = codigoRecuperacionRepository
                .findTopByUsuario_IdAndCodigoAndUsadoFalseOrderByFechaCreacionDesc(usuario.getId(), codigo)
                .orElseThrow(() -> new CodigoInvalidoException("Codigo invalido o expirado"));

        if (registro.getFechaExpiracion().isBefore(OffsetDateTime.now())) {
            throw new CodigoInvalidoException("Codigo invalido o expirado");
        }

        usuario.setPasswordHash(passwordEncoder.encode(nuevaPassword));
        usuarioRepository.save(usuario);

        registro.setUsado(true);
        codigoRecuperacionRepository.save(registro);
    }

    @Override
    public UsuarioResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new EmailYaRegistradoException("Ya existe una cuenta con ese email");
        }

        Usuario usuario = Usuario.builder()
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .build();

        Usuario guardado = usuarioRepository.save(usuario);

        return mapearAResponse(guardado);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CredencialesInvalidasException("Email o contrasena incorrectos"));

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPasswordHash())) {
            throw new CredencialesInvalidasException("Email o contrasena incorrectos");
        }

        String token = jwtUtil.generarToken(usuario.getEmail());

        return LoginResponse.builder()
                .token(token)
                .usuario(mapearAResponse(usuario))
                .build();
    }

    private UsuarioResponse mapearAResponse(Usuario usuario) {
        return UsuarioResponse.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .nombre(usuario.getNombre())
                .temaPreferido(usuario.getTemaPreferido())
                .fechaRegistro(usuario.getFechaRegistro())
                .build();
    }
}