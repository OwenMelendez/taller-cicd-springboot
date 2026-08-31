# Taller CI/CD con Spring Boot — CRUD de Productos

CRUD simple de `Producto` (nombre, precio) construido con Spring Boot, Maven y H2 (base de datos en memoria). Proyecto desarrollado para el taller de CI/CD de Desarrollo Web Avanzado — Tecnológico Comfenalco.

## Tecnologías

- Java 21
- Spring Boot 3.3.4 (Web, Data JPA, Validation)
- H2 Database (en memoria)
- JUnit 5 + MockMvc
- Maven
- JaCoCo (cobertura de código)
- GitHub Actions (CI/CD)

## Endpoints

| Método | Ruta                     | Descripción              |
|--------|--------------------------|---------------------------|
| GET    | `/api/productos`         | Listar todos los productos |
| GET    | `/api/productos/{id}`    | Obtener un producto por id |
| POST   | `/api/productos`         | Crear un producto          |
| PUT    | `/api/productos/{id}`    | Actualizar un producto     |
| DELETE | `/api/productos/{id}`    | Eliminar un producto       |
| GET    | `/api/productos/estado`  | Endpoint de estado (health check) |

## Cómo correr el proyecto

```bash
mvn clean package
mvn spring-boot:run
```

La app queda disponible en `http://localhost:8080`. La consola de H2 está habilitada en `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:tallerdb`, usuario `sa`, sin contraseña).

## Pruebas

```bash
mvn test
```

Incluye pruebas de `ProductoService` (lógica de negocio, incluyendo el cálculo de descuento) y de `ProductoController` (con MockMvc, cubriendo creación, listado y manejo de errores).

## Pipeline de CI/CD

El workflow en `.github/workflows/ci.yml` se ejecuta en cada push y pull request hacia `main`/`develop`, y hace lo siguiente:

1. Compila el proyecto con Maven (JDK 21).
2. Ejecuta las pruebas unitarias.
3. Genera el reporte de cobertura con JaCoCo y lo publica como artefacto descargable de la ejecución.
4. Verifica el manejo de una variable sensible vía GitHub Secrets.


La rama `main` está protegida: los cambios solo se fusionan mediante Pull Request una vez el pipeline pasa en verde.