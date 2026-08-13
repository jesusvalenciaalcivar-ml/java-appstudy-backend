package com.jesus.java_app_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class JavaAppBackendApplication {

	public static void main(String[] args) {
		cargarVariablesDeEntorno();
		SpringApplication.run(JavaAppBackendApplication.class, args);
	}

	private static void cargarVariablesDeEntorno() {
		Path rutaEnv = Path.of(".env");

		if (!Files.exists(rutaEnv)) {
			System.out.println("No se encontro archivo .env, se usaran variables del sistema si existen");
			return;
		}

		try {
			for (String linea : Files.readAllLines(rutaEnv)) {
				linea = linea.trim();

				if (linea.isEmpty() || linea.startsWith("#")) {
					continue;
				}

				int indiceIgual = linea.indexOf('=');
				if (indiceIgual == -1) {
					continue;
				}

				String clave = linea.substring(0, indiceIgual).trim();
				String valor = linea.substring(indiceIgual + 1).trim();

				System.setProperty(clave, valor);
			}
		} catch (IOException e) {
			throw new RuntimeException("No se pudo leer el archivo .env", e);
		}
	}
}