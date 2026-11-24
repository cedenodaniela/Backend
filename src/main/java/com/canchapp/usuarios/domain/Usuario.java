/**
 * Entidad de dominio Usuario.
 * Representa una cuenta de usuario del sistema (CLIENTE o ADMINISTRADOR).
 * No importa Spring, JPA, Jackson ni Firebase. Solo Java puro.
 *
 * @see RN-07
 * @see RN-05
 */
package com.canchapp.usuarios.domain;

import java.time.Instant;
import java.time.ZoneId;

/**
 * Entidad de dominio que modela un usuario del sistema.
 * <p>
 * Atributos y reglas de negocio (RN):
 * - RN-07: Máximo 1 reserva en efectivo activa por cliente.
 * - RN-05: Transferencia: 15 min para subir comprobante (minutosExpiracion).
 * - RF-01: El registro crea solo cuentas de tipo CLIENTE.
 * - Las cuentas ADMINISTRADOR se crean desde el panel (RF-22).
 * <p>
 * Esta clase es pura (POJO) y no tiene dependencias externas.
 */
public class Usuario {

    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password; // Contraseña encriptada con BCrypt
    private String rol; // 'CLIENTE' o 'ADMINISTRADOR'
    private String telefono;
    private Instant fechaCreacion;
    private Instant fechaActualizacion;
    private boolean activo;

    // Constructores públicos
    public Usuario() {}

    /**
     * Constructor para registro (RF-01): solo crea cuentas CLIENTE.
     *
     * @param nombre      Nombre(s) del usuario
     * @param apellido    Apellido(s) del usuario
     * @param email       Correo electrónico (único)
     * @param password    Contraseña en plano (se encriptará internamente)
     * @param telefono    Teléfono opcional
     * @param rol         Siempre 'CLIENTE' en registro público
     */
    public Usuario(String nombre, String apellido, String email, String password, String telefono, String rol) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.rol = rol; // Ya viene filtrado como 'CLIENTE' por el caso de uso
        this.telefono = telefono;
        this.fechaCreacion = Instant.now();
        this.fechaActualizacion = Instant.now();
        this.activo = true;
    }

    // Getters y Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public Instant getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(Instant fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    /**
     * Verifica si el usuario tiene el rol CLIENTE.
     *
     * @return true si es cliente
     */
    public boolean esCliente() { return "CLIENTE".equals(rol); }

    /**
     * Verifica si el usuario tiene el rol ADMINISTRADOR.
     *
     * @return true si es administrador
     */
    public boolean esAdministrador() { return "ADMINISTRADOR".equals(rol); }
}