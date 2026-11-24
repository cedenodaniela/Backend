package com.canchapp.usuarios.infrastructure.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad UserEntity.
 * Proporciona operaciones CRUD básicas y métodos de búsqueda por email.
 */
@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Busca un UserEntity por su email.
     *
     * @param email el email del usuario
     * @return Optional con el UserEntity si existe
     */
    default Optional<UserEntity> findByEmail(String email) {
        return Optional.ofNullable(findByEmailNative(email));
    }

    /**
     * Busca un UserEntity por su email (consulta nativa).
     *
     * @param email el email del usuario
     * @return el UserEntity si existe, null en caso contrario
     */
    @org.springframework.data.jpa.repository.Query("SELECT u FROM UserEntity u WHERE u.email = ?1")
    UserEntity findByEmailNative(String email);

    /**
     * Verifica si ya existe un UserEntity con el email dado.
     *
     * @param email el email a verificar
     * @return true si ya existe un UserEntity con ese email
     */
    default boolean existsByEmail(String email) {
        return existsByEmailNative(email);
    }

    @org.springframework.data.jpa.repository.Query("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.email = ?1")
    boolean existsByEmailNative(String email);
}