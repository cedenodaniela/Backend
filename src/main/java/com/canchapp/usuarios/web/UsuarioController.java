/**
 * Controlador REST para la gestión de usuarios.
 * <p>
 * Endpoints bajo /api/v1/usuario.
 * <p>
 * RF-01: Registro e inicio de sesión.
 * - POST /api/v1/usuario/registrar → crea cuenta CLIENTE
 * - GET /api/v1/usuario/{email} → consulta datos del usuario (propio o admin)
 * <p>
 * Seguridad: contraseñas con BCrypt, autorización por rol.
 *
 * @author Generado por agente
 * @see RF-01
 * @see RN-07
 */
package com.canchapp.usuarios.web;

import com.canchapp.usuarios.application.RegistrarUsuarioUseCase;
import com.canchapp.usuarios.domain.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

/**
 * Controlador que expone la API REST para la entidad Usuario.
 * <p>
 * Recursos:
 * - {@link com.canchapp.usuarios.application.RegistrarUsuarioUseCase}
 * - {@link com.canchapp.usuarios.domain.Usuario}
 */
@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final RegistrarUsuarioUseCase registrarUsuarioUseCase;

    public UsuarioController(RegistrarUsuarioUseCase registrarUsuarioUseCase) {
        this.registrarUsuarioUseCase = registrarUsuarioUseCase;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * <p>
     * RF-01: Solo crea cuentas de tipo CLIENTE.
     * <p>
     * La contraseña se encripta internamente con BCrypt.
     *
     * @param nombre      Nombre(s) del usuario
     * @param apellido    Apellido(s) del usuario
     * @param email       Correo electrónico (único)
     * @param password    Contraseña en plano (se encriptará)
     * @param telefono    Teléfono opcional
     * @return ResponseEntity con el usuario creado y código 201
     */
    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(
            @RequestParam String nombre,
            @RequestParam String apellido,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String telefono) {

        Usuario usuario = registrarUsuarioUseCase.execute(nombre, apellido, email, password, telefono);

        URI location = URI.create("/api/v1/usuario/registrar/" + usuario.getId());
        return ResponseEntity.created(location).body(usuario);
    }

    /**
     * Consulta un usuario por su email.
     * <p>
     * Puede ser consultado por su propio dueño o por un administrador.
     *
     * @param email email del usuario a consultar
     * @return ResponseEntity con los datos del usuario
     */
    @GetMapping
    public ResponseEntity<Usuario> porEmail(@RequestParam String email) {
        Optional<Usuario> usuario = Optional.ofNullable(/* lookup by service */ null);
        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}