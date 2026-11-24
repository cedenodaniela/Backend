/**
 * Adaptador JPA para el repositorio de Usuario.
 * <p>
 * Implementa el puerto definido en UsuarioRepository (domain) usando
 * Spring Data JPA. Mapea entre la entidad JPA y el dominio.
 * <p>
 * Reglas:
 * - La contraseña se encripta con BCrypt antes de guardar.
 * - Las fechas se manejan en la zona America/Guayaquil.
 * - Este es el único módulo que importa Spring y JPA.
 */
package com.canchapp.usuarios.infrastructure;

import com.canchapp.usuarios.domain.Usuario;
import com.canchapp.usuarios.domain.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * Implementación JPA del repositorio de Usuario.
 * <p>
 * Esta clase es la única que toca Spring/JPA. Convierte entre
 * la entidad JPA interna y el objeto de dominio devuelto al resto
 * del sistema.
 */
@Repository
public class UserJpaAdapter implements UsuarioRepository {

    private final com.canchapp.usuarios.infrastructure.entity.UserEntity jpaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserJpaAdapter(com.canchapp.usuarios.infrastructure.entity.UserEntity jpaRepository) {
        this.jpaRepository = jpaRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public Usuario save(Usuario usuario) {
        // Encriptar contraseña con BCrypt antes de guardar
        String encodedPassword = passwordEncoder.encode(usuario.getPassword());
        // Mapear dominio a entidad JPA
        com.canchapp.usuarios.infrastructure.entity.UserEntity entity = toEntity(usuario);
        entity.setPassword(encodedPassword);
        entity = jpaRepository.save(entity);
        return toDomain(entity);
    }

    /**
     * Convierte de entidad JPA a dominio.
     *
     * @param entity entidad JPA
     * @return objeto de dominio
     */
    private Usuario toDomain(com.canchapp.usuarios.infrastructure.entity.UserEntity entity) {
        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());
        usuario.setNombre(entity.getNombre());
        usuario.setApellido(entity.getApellido());
        usuario.setEmail(entity.getEmail());
        usuario.setPassword(entity.getPassword()); // Ya encriptado
        usuario.setRol(entity.getRol());
        usuario.setTelefono(entity.getTelefono());
        usuario.setFechaCreacion(entity.getFechaCreacion() != null
                ? java.time.Instant.from(entity.getFechaCreacion().atZone(java.time.ZoneId.of("America/Guayaquil")).toInstant())
                : null);
        usuario.setFechaActualizacion(entity.getFechaActualizacion() != null
                ? java.time.Instant.from(entity.getFechaActualizacion().atZone(java.time.ZoneId.of("America/Guayaquil")).toInstant())
                : null);
        usuario.setActivo(entity.isActivo());
        return usuario;
    }

    /**
     * Convierte de dominio a entidad JPA.
     *
     * @param usuario objeto de dominio
     * @return entidad JPA lista para persistir
     */
    private com.canchapp.usuarios.infrastructure.entity.UserEntity toEntity(Usuario usuario) {
        com.canchapp.usuarios.infrastructure.entity.UserEntity entity = new com.canchapp.usuarios.infrastructure.entity.UserEntity();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setApellido(usuario.getApellido());
        entity.setEmail(usuario.getEmail());
        entity.setPassword(usuario.getPassword()); // Se encriptará en save()
        entity.setRol(usuario.getRol());
        entity.setTelefono(usuario.getTelefono());
        entity.setFechaCreacion(usuario.getFechaCreacion() != null
                ? java.time.LocalDateTime.ofInstant(usuario.getFechaCreacion(), java.time.ZoneId.of("America/Guayaquil"))
                : null);
        entity.setFechaActualizacion(usuario.getFechaActualizacion() != null
                ? java.time.LocalDateTime.ofInstant(usuario.getFechaActualizacion(), java.time.ZoneId.of("America/Guayaquil"))
                : null);
        entity.setActivo(usuario.isActivo());
        return entity;
    }
}