# Proyecto ApiRest

Este proyecto consiste en el desarrollo de una API REST utilizando el framework Spring Boot para la gestión de operaciones de venta en el que se incluyen validaciones para ciertas ejecuciones. 

## Requisitos Técnicos

Para la correcta ejecución y despliegue del proyecto, se requiere el siguiente entorno:

* **Lenguaje:** Java 21
* **Gestor de dependencias:** Maven
* **Framework principal:** Spring Boot 4.0.5
* **Base de Datos:** PostgreSQL
* **Librerías adicionales:**
    * Lombok 
    * Spring Web 
    * Spring Data JPA 
    * Validation 
    * PostgreSQL Driver
    * Spring Doc

*(Nota: Todas las dependencias se encuentran detalladas en el archivo `pom.xml`)*

## Configuración de la Base de Datos

El sistema utiliza una base de datos relacional PostgreSQL.

* **Nombre de la base de datos:** `ProyectoApiRest`
* **Esquema de tablas:** Se encuentra en el directorio `/database/tescha.sql`
* **Datos iniciales (registros):** Se encuentra en el directorio `/database/data.sql`

## Configuración del Entorno (application.properties)

El archivo de configuración principal se ubica en la ruta: `src/main/resources/application.properties`

### Contenido de la configuración:

```properties
spring.application.name=ApiRest

# Conexion a Postgres local
# Nota: Ajustar puerto y nombre de la BD si es necesario
spring.datasource.url=jdbc:postgresql://localhost:5432/ProyectoApiRest
spring.datasource.username=--USUARIO--
spring.datasource.password=--CONTRASEÑA--
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jackson.mapper.sort-properties-alphabetically=false
```
*(Nota: Es indispensable sustituir --USUARIO-- y --CONTRASEÑA-- por las credenciales locales de su instancia de PostgreSQL para establecer la conexión correctamente.)* 

## Pruebas de Request y Response (Postman)
La documentación de las pruebas funcionales, incluyendo los cuerpos de solicitud (JSON) y las respuestas esperadas, se encuentra en el directorio /postman.

El archivo .json contenido en dicha carpeta debe ser importado en Postman para ejecutar la colección de pruebas.

## Documentación con Swagger
La API cuenta con documentación interactiva para la confirmación y prueba de los endpoints en tiempo de ejecución.

Una vez que la aplicación esté corriendo localmente, se puede acceder a la interfaz de Swagger mediante la siguiente URL en cualquier navegador:

http://localhost:8080/swagger-ui/index.html

## Estructura del Proyecto
* **/src:** Contiene el código fuente organizado por capas (Entities, Repository, Services, Controller, etc.).

* **/database:** Contiene los scripts SQL necesarios para la inicialización del sistema (Tablas y Registros).

* **/postman:** Contiene la colección de peticiones para validación externa (Pruebas).

## Endpoints utilizados
* **UserController:**
  
      * @GetMapping: `GET /api/users` - Listar todos los usuarios registrados con soporte de paginación.
  
      * @PostMapping `POST /api/users` - Crear un nuevo usuario en la base de datos

* **ProductController:**
  
      * @GetMapping: `GET /api/products` - Obtener la lista de productos paginada.

      * @PostMapping: `POST /api/products` - Registrar un nuevo producto en el catálogo

      * @PutMapping("/{id}"): `PUT /api/products/{id}` - Actualizar los datos de un producto existente.

      * @GetMapping("/{id}"): `GET /api/products/{id}` - Buscar un producto específico por su ID.
      
* **OrderController:**

      * GetMapping: `GET /api/orders` - Consultar todas las órdenes de venta realizadas.
 
      * @PostMapping: `POST /api/orders` - Generar una nueva orden de venta.
