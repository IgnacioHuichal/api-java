package cl.lupoconecta.apijava.users.infrastructure.controller;

import cl.lupoconecta.apijava.users.application.service.UserService;
import cl.lupoconecta.apijava.users.domain.model.User;
import cl.lupoconecta.apijava.users.infrastructure.dto.UserRequestDto;
import cl.lupoconecta.apijava.users.infrastructure.dto.UserResponseDto;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

/**
   * Spring detecta con @restcontroller que esta clase es un controlador de Spring MVC, 
   * lo que permite manejar las solicitudes HTTP y enviar respuestas al cliente.
   * 
   * cuando haces un POST http://localhost:8080/api/users
   * Aquí Spring detecta la ruta `/api/users`.
   * El método `createUser(@RequestBody @Valid UserRequestDto dto)` recibe el JSON de entrada (name, email).
   * El DTO se valida y se transforma en un objeto de dominio `User`.
   * 
   * Resumen:
   * 1 Recibe el JSON con los datos del usuario a crear.
   * 2 Valida y convierte ese JSON a un DTO que luego transforma a entidad.
   * 3 Pasa la entidad al servicio para persistencia.
   * 4 Devuelve un DTO de respuesta con los datos del usuario guardado.
   */

/**
 * `@RestController`: Esta anotación indica que esta clase es un controlador web REST,
 * capaz de manejar solicitudes HTTP y responder con datos JSON (o en otros formatos).
 * 
 * `@RequestMapping("/api/users")`: Define la ruta base para todas las acciones (métodos)
 * en esta clase, en este caso, todas las URLs que empiecen con `/api/users`.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    // Declara una dependencia __final__ al servicio de usuarios (`UserService`),
    // que gestiona la lógica de negocio relacionada con usuarios.
    private final UserService userService;  

    /**
     * Constructor para inyectar el servicio de usuarios
     * Constructor público donde se realiza la inyección de dependencias
     * (usualmente Spring inyecta automáticamente el bean `UserService`).
     * Inicializa la variable `userService` para que esté disponible en toda la clase.
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * - `@PostMapping`: Mapea la función al verbo HTTP POST en la ruta base (`/api/users`).
     * 
     * `public UserResponseDto createUser(...)`: Método público que crea un usuario y
     * devuelve un DTO con los datos del usuario creado.
     * 
     * `@RequestBody @Valid UserRequestDto dto`: El cuerpo de la petición HTTP debe ser un JSON
     * que se deserializa en `UserRequestDto`. La anotación `@Valid` activa la validación
     * automática según las reglas definidas en `UserRequestDto`.
     */ 
    @PostMapping
    public UserResponseDto createUser(@RequestBody @Valid UserRequestDto dto) {
        User user = new User(
            dto.getName(),
            dto.getEmail()
        );

        // Crea una nueva instancia del objeto de dominio `User`
        // con los datos recibidos dentro del DTO `dto` (nombre y email).
        User createdUser = userService.createUser(user);
    
        // Llama al método del servicio para crear/persistir el usuario.
        //`createdUser` contiene el usuario persistido, con su `id` generado por la base de datos.
        // Devuelve un objeto DTO de respuesta con los datos del usuario creado,
        // para enviar la respuesta JSON al cliente.
        return new UserResponseDto(createdUser.getId(), createdUser.getName(), createdUser.getEmail());
    }
}