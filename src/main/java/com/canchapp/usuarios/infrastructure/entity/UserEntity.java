/**
 * Entidad JPA que mapea a la tabla 'usuario' de la base de datos.
 * <p>
 * Esta es la entidad que Spring Data JPA persiste. Es la única clase
 * del proyecto que importa javax.persistence y anotaciones Spring.
 * <p>
 * Tabla: usuario (RN-07, RF-01)
 * <p>
 * Restricciones:
 * - email UNIQUE (no superponer usuarios con mismo email)
 * - rol CHECK ('CLIENTE', 'ADMINISTRADOR')
 * - activo por defecto TRUE
 *
 * @author Generado por agente
 * @see RN-07
 * @see RF-01
 */
package com.canchapp.usuarios.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;

/**
 * Entidad JPA UserEntity que mapea a la tabla 'usuario'.
 * Contiene los atributos y mapeo de base de datos para el usuario del sistema.
 */
@Entity
@Table(name = "usuario", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"email"})
})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password; // Encriptado con BCrypt

    @Column(name = "rol", nullable = false, length = 20)
    private String rol; // 'CLIENTE' o 'ADMINISTRADOR' como String

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    @Column(name = "activo", nullable = false)
    private boolean activo = true;

    // Getters and Setters

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

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    public boolean isActivo() { return activo; }

    public void setActivo(boolean activo) { this.activo = activo; }
}