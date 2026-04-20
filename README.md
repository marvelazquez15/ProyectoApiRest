# Proyecto ApiRest
Este proyecto consiste en el desarrollo de una API REST utilizando el framework Spring Boot para la gestión de operaciones de venta y administración de recursos.

## Requisitos Técnicos
Para la correcta ejecución y despliegue del proyecto, se requiere el siguiente entorno:
* Lenguaje: Java 21
* Gestor de dependencias: Maven
* Framework principal: Spring Boot 4.0.5
* Base de Datos: PostgreSQL
* Librerías adicionales:
    * Lombok 
    * Spring Web 
    * Spring Data JPA 
    * Validation 
    * PostgreSQL Driver
    * Spring Doc
    * (ESTAS DEPENDENCIAS SE ENCUENTRAN EN EL archivo pom.mxl)

## Configuración de la Base de Datos
El sistema utiliza una base de datos relacional PostgreSQL.
* Nombre de la base de datos: ProyectoApiRest
* Ubicación del esquema de tablas: Se encuentra en el directorio *`/database/tescha.sql`*
* Ubicación de los datos iniciales (registros): Se encuentra en el directorio *`/database/data.sql*

## Configuración del Entorno (application.properties)
El archivo de configuración principal se ubica en la ruta: *`src/main/resources/application.properties`*

Contenido de la configuración:
spring.application.name=ApiRest
## Conexion a Postgres local
spring.datasource.url=jdbc:postgresql://localhost:5432/ProyectoApiRest --PUERTO Y NOMBRE DE LA BD-- (EN CASO DE TENER OTRO PUERTO O NOMBRE -CAMBIARLO-)
spring.datasource.username= --USUARIO--
spring.datasource.password= --CONTRASEÑA--
spring.datasource.driver-class-name=org.postgresql.Driver
-- JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jackson.mapper.sort-properties-alphabetically=false
  
  NOTA: Es necesario sustituir --USUARIO-- y --CONTRASEÑA-- por las credenciales locales de su instancia de PostgreSQL.

## Pruebas de Request y Response (Postman)
La documentación de las pruebas funcionales, incluyendo los cuerpos de solicitud (JSON) y las respuestas esperadas, se encuentra en el directorio /postman. 
El archivo .json contenido en dicha carpeta debe ser importado en Postman para ejecutar la colección de pruebas.

## Documentación con Swagger
La API cuenta con documentación interactiva para la confirmación y prueba de los endpoints en tiempo de ejecución. 
Una vez que la aplicación esté corriendo localmente, se puede acceder a la interfaz de Swagger mediante la siguiente URL en cualquier navegador:
  -- http://localhost:8080/swagger-ui/index.html --

## ESTRUCTURA ##
/src: Contiene el código fuente organizado por capas (Entitis, Repository, Services y Controller, etc...).
/database: Contiene los scripts SQL necesarios para la inicialización del sistema (Tablas y Regsitros).
/postman: Contiene /postman: Contiene la colección de peticiones para validación externa.la colección de peticiones para validación externa (Pruebas)
