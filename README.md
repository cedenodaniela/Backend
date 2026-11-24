# CanchApp · Backend

API REST del sistema de reservas del **Complejo Deportivo Calceta** (Calceta, Manabí, Ecuador).

Spring Boot 3 · Java 17 · PostgreSQL · Flyway · Spring Security + JWT · Spring WebSocket (STOMP).

Este repositorio contiene **solo el backend**. La app móvil vive en `../Frontend` y el panel de administración en `../AdminWeb`.

## Documentación

La documentación general del proyecto **no vive aquí**: está en la carpeta de trabajo, un nivel arriba.

| Documento | Ruta |
|---|---|
| Guía para agentes y convenciones | `../AGENTS.md` |
| DERCAS (fuente de verdad) | `../DERCAS_CanchApp.pdf` |
| Addendum v0.3 (panel web) | `../docs/DERCAS_CanchApp_v0.3_addendum.md` |
| Planificación de las 5 iteraciones | `../docs/PLANIFICACION.md` |

## Puesta en marcha

```bash
docker compose up -d db
./mvnw spring-boot:run
```

## Pruebas

```bash
./mvnw test
./mvnw verify
./mvnw javadoc:javadoc
```

## Flujo de trabajo

Ramas `main` y `develop`; una rama por tarea (`feat/RF-07-reserva-bloqueo`) y un Pull Request por RF. **Los issues y milestones se gestionan en este repositorio**, aunque el RF toque también a los clientes. Ver `../AGENTS.md` §12.

## Estado

En construcción. La primera iteración del plan crea el esqueleto del proyecto, `docker-compose.yml` y la migración `V1`.
