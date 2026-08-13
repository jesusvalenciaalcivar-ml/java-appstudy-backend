package com.jesus.java_app_backend.service.impl;

import com.jesus.java_app_backend.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void enviarCodigoRecuperacion(String destinatario, String codigo) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Recupera tu contrasena - JavaAppStudy");
        mensaje.setText(
                "Tu codigo de recuperacion es: " + codigo + "\n\n" +
                        "Este codigo expira en 15 minutos.\n\n" +
                        "Si no solicitaste este cambio, puedes ignorar este correo."
        );
        mailSender.send(mensaje);
    }
}