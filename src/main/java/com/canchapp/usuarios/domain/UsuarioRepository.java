/**
 * Interfaz de puerto para la persistencia de Usuario.
 * <p>
 * Esta es una interfaz de dominio pura que define las operaciones
 * necesarias para el acceso a datos de usuario. La implementación
 * real vive en el módulo infrastructure (JPA).
 * <p>
 * Reglas (RN):
 * - RN-07: Parámetro maxReservasEfectivo configurable en PoliticaReserva.
 * - RF-01: Solo se crean cuentas CLIENTE desde el registro.
 *
 * @author Generado por agente
 * @see RN-07
 * @see RF-01
 */
package com.canchapp.usuarios.domain;

import java.util.Optional;

/**
 * Contrato de persistencia para la entidad Usuario.
 * <p>
 * Nota: Los métodos usan tipos de dominio puros (no JPA).
 * La firma está diseñada para que la implementación JPA la adapte
 * sin que el dominio necesite saber de@Entity o @Repository.
 */
public interface UsuarioRepository {

    /**
     * Busca un usuario por su email.
     *
     * @param email correo electrónico del usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verifica si ya existe un usuario con el email dado.
     *
     * @param email correo electrónico a verificar
     * @return true si ya existe un usuario con ese email
     */
    boolean existsByEmail(String email);

    /**
     * Guarda un usuario en la base de datos.
     *
     * @param usuario entidad usuario a persistir
     * @return usuario persistido con su ID asignado
     */
    Usuario save(Usuario usuario);
}