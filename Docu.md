__Spring Boot Devtools:__ Permite recarga automática (“hot restart”) cuando detecta cambios en archivos fuente, agilizando el desarrollo.

pom.xml no tienes ninguna dependencia para motor de vistas como Thymeleaf o Freemarker.
Si tienes archivos en `src/main/resources/templates/` esos no serán procesados como plantillas.

<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

Para mostrar una pantalla por defecto

1. __Crear Controlador para la ruta raíz ("/")__

Define un controlador que maneje la raíz y devuelva una vista o contenido:

```java
@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index"; // nombre de la plantilla html ubicada en src/main/resources/templates/index.html
    }
}
```

En Spring, la diferencia entre `@Controller` y `@RestController` es principalmente cómo se manejan las respuestas HTTP:

- `@Controller`:

  - Marca la clase como controladora tradicional de Spring MVC.
  - Se usa para manejar peticiones y retornar vistas HTML o plantillas (por ejemplo, JSP, Thymeleaf).
  - Los métodos del controlador devuelven nombres de vistas o modelos, y no directamente datos JSON.
  - Para enviar JSON, hay que usar `@ResponseBody` en los métodos o en la clase para serializar las respuestas.

- `@RestController`:

  - Es una combinación de `@Controller` + `@ResponseBody`.
  - Indica que todos los métodos de esta clase devolverán directamente objetos JSON (o XML) en la respuesta HTTP.
  - Se usa principalmente para construir APIs RESTful donde solo se intercambian datos y no vistas.

__Cuándo usar cada uno:__

- Usa `@Controller` cuando tu aplicación entregue vistas o páginas web renderizadas desde el servidor.
- Usa `@RestController` cuando desarrolles API REST donde el servidor solo envía y recibe datos JSON (común en aplicaciones SPA, móviles, o microservicios).

En tu proyecto con el endpoint `/api/users` que devuelve objetos y espera JSON, es adecuado usar `@RestController` para simplificar y evitar tener que poner `@ResponseBody` en cada método.



## Escalar proyecto con entidades y modulos
cuando introduces una nueva entidad en tu base de datos (por ejemplo Tournament), siguiendo la arquitectura y patrón que tienes, deberías replicar la misma estructura modular que usas para User. Esto incluye:

- __Nuevo Modelo de Dominio (`Tournament`)__

  - Crearás una clase en `domain/model` que defina la entidad `Tournament` y sus campos.

- __Puerto de Dominio (`TournamentRepository`)__

  - Crearás una interfaz tipo `Repository` en `domain/port` que defina los métodos para acceder a torneos, según reglas de negocio (save, find, etc.).

- __Adaptador de Infraestructura (`TournamentRepositoryMongo`)__

  - Implementarás esa interfaz en `infrastructure/repository`, con la lógica concreta para MongoDB.

- __Repositorio Spring Data (opcional)__

  - Extenderás `MongoRepository` para `Tournament` para manejo CRUD.

- __Servicio de Aplicación (`TournamentService`)__

  - Crearás la capa de servicio que orquesta la lógica entre controlador y repositorios.

- __Controlador REST (`TournamentController`)__

  - Crearás un controlador para exponer endpoints REST para torneos (`/api/tournaments`).



## Estructura de carpetas recomendada para Spring Boot + API REST + Front

Si no quieres arquitectura hexagonal y prefieres una estructura más simple, mantenible y natural para Spring Boot, te conviene organizar el proyecto por **feature**.

### Estructura sugerida

```text
src/
└── main/
    ├── java/
    │   └── cl/
    │       └── lupoconecta/
    │           └── apijava/
    │               ├── ApijavaApplication.java
    │               │
    │               ├── common/
    │               │   ├── config/
    │               │   │   ├── CustomMongoConfig.java
    │               │   │   ├── SwaggerConfig.java
    │               │   │   └── WebConfig.java
    │               │   ├── exception/
    │               │   │   ├── GlobalExceptionHandler.java
    │               │   │   └── ResourceNotFoundException.java
    │               │   └── util/
    │               │       └── DateUtils.java
    │               │
    │               ├── home/
    │               │   └── controller/
    │               │       └── HomeController.java
    │               │
    │               ├── users/
    │               │   ├── controller/
    │               │   │   └── UserController.java
    │               │   ├── dto/
    │               │   │   ├── UserRequestDto.java
    │               │   │   └── UserResponseDto.java
    │               │   ├── model/
    │               │   │   └── User.java
    │               │   ├── repository/
    │               │   │   └── UserRepository.java
    │               │   └── service/
    │               │       └── UserService.java
    │               │
    │               ├── tournaments/
    │               │   ├── controller/
    │               │   │   └── TournamentController.java
    │               │   ├── dto/
    │               │   │   ├── TournamentRequestDto.java
    │               │   │   └── TournamentResponseDto.java
    │               │   ├── model/
    │               │   │   └── Tournament.java
    │               │   ├── repository/
    │               │   │   └── TournamentRepository.java
    │               │   └── service/
    │               │       └── TournamentService.java
    │               │
    │               ├── teams/
    │               │   ├── controller/
    │               │   │   └── TeamController.java
    │               │   ├── dto/
    │               │   │   ├── TeamRequestDto.java
    │               │   │   └── TeamResponseDto.java
    │               │   ├── model/
    │               │   │   └── Team.java
    │               │   ├── repository/
    │               │   │   └── TeamRepository.java
    │               │   └── service/
    │               │       └── TeamService.java
    │               │
    │               └── notifications/
    │                   ├── service/
    │                   │   ├── EmailService.java
    │                   │   └── NotificationService.java
    │                   └── dto/
    │                       └── EmailRequestDto.java
    │
    └── resources/
        ├── application.properties
        ├── static/
        │   ├── index.html
        │   ├── css/
        │   │   └── style.css
        │   ├── js/
        │   │   └── app.js
        │   └── images/
        │       └── logo.png
        └── templates/
            ├── index.html
            └── error.html
```

---

## Qué significa cada carpeta

### `common/`
Aquí dejas todo lo reutilizable y transversal:

- `config/`: configuración global
- `exception/`: manejo global de errores
- `util/`: utilidades comunes

### `home/`
Para controladores de navegación o páginas base como `/`.

### `users/`, `tournaments/`, `teams/`
Cada entidad o feature debería tener su propio bloque:

- `controller/`: endpoints REST
- `dto/`: clases para request y response
- `model/`: entidad Mongo
- `repository/`: interface que extiende `MongoRepository`
- `service/`: lógica de negocio

### `notifications/`
Un ejemplo de feature de soporte, no necesariamente una entidad principal de DB.

---

## Ejemplo de crecimiento real

Si mañana agregas `Tournament`, normalmente crearías:

- `Tournament.java`
- `TournamentRepository.java`
- `TournamentService.java`
- `TournamentController.java`
- `TournamentRequestDto.java`
- `TournamentResponseDto.java`

Eso está bien. En Spring Boot es normal.

---

## Recomendación práctica para escalar

Si tu proyecto crecerá con varias entidades:

1. **Agrupa por feature y no por capa global**
   - Mejor `users/controller`, `users/service`
   - Que `controller/`, `service/`, `repository/` globales mezclando todo

2. **Mantén 1 repository por entidad**
   - `UserRepository`
   - `TournamentRepository`
   - `TeamRepository`

3. **Usa DTOs para entrada y salida**
   - Evita exponer directamente tus entidades siempre

4. **Deja `common/` solo para cosas realmente compartidas**
   - config
   - exceptions
   - utils

5. **Si usas front estático simple**
   - usa `resources/static`

6. **Si usas vistas dinámicas con Thymeleaf**
   - usa `resources/templates`

---

## Estructura mínima por entidad

Por cada nueva entidad, lo mínimo recomendable suele ser:

```text
feature/
├── controller/
├── dto/
├── model/
├── repository/
└── service/
```

Ejemplo:

```text
tournaments/
├── controller/TournamentController.java
├── dto/TournamentRequestDto.java
├── dto/TournamentResponseDto.java
├── model/Tournament.java
├── repository/TournamentRepository.java
└── service/TournamentService.java
```

---
