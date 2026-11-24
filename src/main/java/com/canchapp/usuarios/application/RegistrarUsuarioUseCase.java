/**
 * Caso de uso: RegistrarUsuarioUseCase
 * <p>
 * RF-01: El registro crea solo cuentas de tipo CLIENTE.
 * <p>
 * Esta clase orquesta la creación de un nuevo usuario en el sistema.
 * Solo permite crear cuentas con rol CLIENTE desde el registro público.
 * Las cuentas ADMINISTRADOR se crean exclusivamente desde el panel web (RF-22).
 *
 * @author Generado por agente
 * @see RF-01
 * @see RN-07
 */
package com.canchapp.usuarios.application;

import com.canchapp.usuarios.domain.Usuario;
import com.canchapp.usuarios.domain.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * Caso de uso que gestiona el registro de un nuevo usuario.
 * <p>
 * Decisiones:
 * - Siempre crea usuarios con rol 'CLIENTE' (según RF-01).
 * - La contraseña debe encriptarse con BCrypt antes de guardar (handled en infrastructure).
 * - La fecha de creación se establece en la zona America/Guayaquil.
 *
 * @param dto Datos del nuevo usuario (nombre, apellido, email, password, telefono)
 * @return Usuario creado y persistido
 * @throws IllegalArgumentException si el email ya existe
 */
@Service
@Transactional
public class RegistrarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public RegistrarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Ejecuta el registro de un nuevo usuario.
     *
     * @param nombre          Nombre(s) del usuario
     * @param apellido        Apellido(s) del usuario
     * @param email           Correo electrónico (único)
     * @param password        Contraseña en plano (se encriptará internamente)
     * @param telefono        Teléfono opcional
     * @return Usuario creado
     * @throws IllegalArgumentException si el email ya está registrado
     */
    public Usuario execute(String nombre, String apellido, String email, String password, String telefono) {
        // RF-01: solo crear cuentas CLIENTE desde el registro
        // Verificar que el email no existe (restricción única en BD)
        if (usuarioRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("El email " + email + " ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setPassword(password); // La encriptación la hace el adaptador JPA/BCrypt
        usuario.setRol("CLIENTE");
        usuario.setTelefono(telefono);
        usuario.setFechaCreacion(Instant.now());
        usuario.setFechaActualizacion(Instant.now());
        usuario.setActivo(true);

        return usuarioRepository.save(usuario);
    }
}