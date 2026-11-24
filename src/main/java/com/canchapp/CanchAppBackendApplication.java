/**
 * Clase principal de la aplicación Spring Boot CanchApp Backend.
 * <p>
 * Inicia el servidor API REST en el puerto configurado (por defecto 8080).
 * Esta es la entrada principal del aplicativo.
 *
 * @author Generado por agente
 * @see org.springframework.boot.SpringBootApplication
 */
package com.canchapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Configuración principal de la aplicación CanchApp.
 * <p>
 * Annotations:
 * - @SpringBootConfiguration: Marca esta clase como fuente de definiciones de beans
 * - @EnableJpa: Habilita el soporte de JPA en Spring Boot
 * - @ComponentScan: Escanea los paquetes del proyecto para detectar componentes
 */
@SpringBootApplication(scanBasePackages = {"com.canchapp"})
public class CanchAppBackendApplication {

    /**
     * Punto de entrada principal de la aplicación.
     * <p>
     * Ejecuta Spring Boot, que inicia el servidor web embebido (Tomcat) y
     * carga todos los componentes configurados (repositorios, servicios,
     * controladores, etc.).
     *
     * @param args argumentos de línea de comandos (no utilizados habitualmente)
     */
    public static void main(String[] args) {
        SpringApplication.run(CanchAppBackendApplication.class, args);
    }
}