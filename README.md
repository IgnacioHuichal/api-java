# API Java Spring Boot – Ejecución con Docker

Este proyecto Spring Boot está preparado para ser ejecutado con Docker y Docker Compose, incluyendo dependencias externas como MongoDB y RabbitMQ.

---

## 1. Prerrequisitos

Debes tener instalados en tu sistema:

- **Docker**
- **Docker Compose** (v2+ viene incluido en Docker Desktop moderno)
- (Opcional) **Maven** si quieres compilar el JAR fuera del contenedor.

---

## 2. Estructura esperada

El directorio del proyecto debe incluir:

- `Dockerfile`
- `docker-compose.yml`
- `pom.xml`
- Carpeta `src/`
- (Opcional) El JAR ya compilado en `target/` si usas Maven externo.

---

## 3. Levantar todo con Docker Compose

### 3.1. Construir y levantar servicios

Desde la raíz del proyecto (`/home/ignacio/learning-java/apijava`):

```bash
docker compose up --build
```

Esto levantará:

- La app Java (puerto 8080 expuesto)
- MongoDB (puerto 27017)
- RabbitMQ (puerto 5672 + UI en 15672)

### 3.2. Ejecutar en segundo plano

```bash
docker compose up -d
```

### 3.3. Apagar servicios

```bash
docker compose down
```

---

## 4. Verificar que la API está corriendo

1. Verifica que el contenedor de la app está “up”:
   ```bash
   docker ps
   ```

2. Chequea logs de la app Spring Boot:
   ```bash
   docker logs -f NOMBRE_O_ID_DEL_CONTENEDOR
   # por ej.: apijava-backend (según tu docker-compose.yml)
   ```

3. Prueba el endpoint básico (suele ser `/hola` o `/`):
   ```bash
   curl http://localhost:8080/
   # o
   curl http://localhost:8080/hola
   ```

4. Accede a la UI de administración de RabbitMQ desde tu navegador:
   - [http://localhost:15672](http://localhost:15672)
   - User/pass por defecto: definidos en `docker-compose.yml` (ej. `user/password`).

---

## 5. Variables de entorno y configuración

Las credenciales de MongoDB y RabbitMQ, nombres de base de datos, etc., están definidas en `docker-compose.yml` como variables de entorno y deben estar referenciadas en `src/main/resources/application.properties` para Spring Boot.

Ejemplo en `application.properties`:

```properties
spring.data.mongodb.uri=${SPRING_DATA_MONGODB_URI}
spring.rabbitmq.host=${SPRING_RABBITMQ_HOST}
spring.rabbitmq.port=${SPRING_RABBITMQ_PORT}
spring.rabbitmq.username=${SPRING_RABBITMQ_USERNAME}
spring.rabbitmq.password=${SPRING_RABBITMQ_PASSWORD}
```

---

## 6. Comandos útiles adicionales

- Ver logs de todos los servicios:
  ```bash
  docker compose logs -f
  ```
- Ver solo logs de la app:
  ```bash
  docker logs -f apijava-backend
  ```
- Acceder a una terminal dentro del contenedor app:
  ```bash
  docker exec -it apijava-backend sh
  ```
- Listar volúmenes:
  ```bash
  docker volume ls
  ```
- Limpiar volúmenes parados:
  ```bash
  docker volume prune
  ```

---

## 7. Notas

- Si cambias dependencias de Java o código fuente, recuerda reconstruir la imagen con `docker compose up --build`.

---

Con estos pasos puedes levantar el entorno completo de la API y sus dependencias con un solo comando Docker Compose.
