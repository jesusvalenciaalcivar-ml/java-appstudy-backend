package com.jesus.java_app_backend.service;

public interface EmailService {
    void enviarCodigoRecuperacion(String destinatario, String codigo);
}