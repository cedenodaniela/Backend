# AGENTS.md — CanchApp · Backend

API REST en Spring Boot 3 (Java 17) del proyecto **CanchApp**. Esta es la guía **breve** de este repositorio.

> **La guía completa vive fuera de este repositorio**, en la carpeta de trabajo, un nivel arriba:
> [`../AGENTS.md`](../AGENTS.md) · DERCAS: [`../DERCAS_CanchApp.pdf`](../DERCAS_CanchApp.pdf) · addendum v0.3: [`../docs/DERCAS_CanchApp_v0.3_addendum.md`](../docs/DERCAS_CanchApp_v0.3_addendum.md) · plan: [`../docs/PLANIFICACION.md`](../docs/PLANIFICACION.md).
>
> Si este archivo y la guía completa se contradicen, manda la guía completa y, sobre todo, el **DERCAS**.

## Qué se construye aquí

Todo lo que es **regla de negocio**: dominio, casos de uso, persistencia, tareas programadas y API REST. Los dos clientes (`../Frontend` y `../AdminWeb`) **no calculan reglas**; consumen esta API.

## Estructura

Un proyecto Maven y un paquete por módulo, cada uno con sus cuatro capas:

```
src/main/java/com/canchapp/
├── reservas/         domain · application · infrastructure · web
├── pagos/
├── canchas/
├── horarios/
├── usuarios/
├── notificaciones/
├── reportes/
├── configuracion/
└── shared/           utilidades transversales mínimas
```

- `domain` **no importa** Spring, JPA, Jackson ni Firebase. Solo Java puro.
- `application` tiene un caso de uso por clase y depende solo de `domain`.
- `infrastructure` implementa los puertos (JPA, Firebase, SMTP, WebP).
- `web` tiene los controladores y los DTOs (`record`). **Nunca** expongas entidades del dominio ni de JPA.

## Reglas que no se rompen

- Nombres del dominio en **español** (`Reserva`, `cancelar()`, `saldoFavor`); los términos técnicos en inglés.
- Dinero con `BigDecimal` y escala 2; **nunca** `double`. Fechas con `java.time` en `America/Guayaquil`.
- Los valores numéricos son parámetros de `PoliticaReserva`; nunca los escribas fijos.
- Las transiciones de `Reserva` se validan **dentro de la entidad**.
- **Nunca doble reserva (RN-04)**: la restricción única viaja en **la misma migración** que crea la tabla de bloques reservados.
- Cada cambio de esquema es una migración **nueva** de Flyway (`V{n}__descripcion.sql`). Nunca edites una ya aplicada.
- **Javadoc obligatorio** con `@see RN-xx`. API bajo `/api/v1`, recursos en plural.
- Errores: excepciones de dominio traducidas en un `@RestControllerAdvice` con `codigo` y `mensaje` en español.

## Comandos

```bash
docker compose up -d db
./mvnw spring-boot:run
./mvnw test
./mvnw verify          # pruebas + cobertura
./mvnw javadoc:javadoc
```

## Pruebas y cierre

- Cada RN tocada tiene al menos una prueba en `domain` o `application`.
- Cobertura mínima del dominio: **70 %**.
- RN-04 exige prueba de concurrencia: 10 solicitudes simultáneas sobre el mismo bloque producen una sola reserva.
- Antes de commitear: `./mvnw test` en verde. Un commit por cambio lógico, Conventional Commits en español, citando el RF.

## Contrato con los clientes

Este repositorio publica el `openapi.json` (springdoc). Es el **contrato** que `Frontend` y `AdminWeb` validan en su CI: si cambias un DTO, el cambio se detecta en el Pull Request.

## Seguridad

Contraseñas con BCrypt, autorización por rol en cada endpoint y HTTPS. Un comprobante solo lo ven su dueño y el administrador, servido por un endpoint autenticado. **Nunca** subas secretos: credenciales de Firebase, contraseñas de base de datos ni la clave JWT.
