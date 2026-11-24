-- V001__usuarios.sql
-- Migración inicial: tabla de usuarios con restricciones y comentarios
-- DERCAS §4.1, RF-01 · RN-07 (máximo 1 reserva en efectivo activa por cliente)
-- RN-05: contraseñas con BCrypt, RN-07: maxReservasEfectivo parameter

CREATE TABLE usuario (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) NOT NULL CHECK (rol IN ('CLIENTE', 'ADMINISTRADOR')),
    telefono VARCHAR(20),
    fecha_creacion TIMESTAMP NOT NULL DEFAULT NOW(),
    fecha_actualizacion TIMESTAMP NOT NULL DEFAULT NOW(),
    activo BOOLEAN NOT NULL DEFAULT TRUE,
    CONSTRAINT uq_usuario_email UNIQUE (email)
);

-- Comentario: tabla de usuarios para CanchApp.
-- La contraseña se encripta con BCrypt (nunca en plano).
-- El rol 'ADMINISTRADOR' se siembra al instalar el sistema (RF-22).
-- El rol 'CLIENTE' se crea automáticamente en el registro RF-01.