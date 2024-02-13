# Aseguradora - Backend

Proyecto de API REST que funciona como backend del sistema **Aseguradora**. Está desarrollado en Spring Boot 3 y Java 17.

## Configuración IDE

Este proyecto usa las librerías [Lombok](https://projectlombok.org/) y [MapStruct](https://mapstruct.org/) para evitar el boilerplate de Java. Algunos IDE, como por ejemplo Eclipse, necesitan de extensiones para que sean compatibles con estas librerias.

Para Eclipse:

- Lombok: [https://projectlombok.org/setup/eclipse](https://projectlombok.org/setup/eclipse)
- MapStruct: [https://marketplace.eclipse.org/content/mapstruct-eclipse-plugin](https://marketplace.eclipse.org/content/mapstruct-eclipse-plugin)

## Correr proyecto

Primero se deben descargar las dependencias con el comando:

```
mvn dependency:resolve
```

Luego para correr el proyecto:

```
mvn spring-boot:run
```

Una vez ejecutado se podrá acceder al backend en la URL [http://localhost:8082](http://localhost:8082).

Por defecto usará el profile *local* el cual tiene asignado el puerto 8082 para evitar conflicto con el puerto de otras APIs, y conectará con la base de datos local.

Para saber los endpoints de está API REST, la paginá de inicio redirecciona a Swagger, el cual documenta cada endpoint con su descripción y body requerido en el request.