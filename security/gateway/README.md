# Aseguradora - Gateway

Proyecto API Gateway del sistema **Aseguradora**. Esta desarrollado en Spring Cloud Gateway con WebFlux y Java 17.

## Correr proyecto

Primero se deben descargar las dependencias con el comando:

```
mvn dependency:resolve
```

Luego para correr el proyecto:

```
mvn spring-boot:run
```

Una vez ejecutado se podrá acceder al gateway en la URL [http://localhost:9000](http://localhost:9000).

Por defecto usará el profile *local* el cual tiene asignado el puerto 9000 para evitar conflicto con el puerto de otras APIs, y conectará con la base de datos local.

Tener en cuenta que este proyecto es un gateway, por lo que no tiene un Swagger con los endpoints de cada servicio al que redirecciona.

Actualmente, al estar hecho en WebFlux no es compatible con Spring Boot Devtools para el livereload, por lo que se debe detener e iniciar el comando `mvn spring-boot:run` cada vez que se hace un cambio en el código. Ver: [https://github.com/spring-cloud/spring-cloud-gateway/issues/3216](https://github.com/spring-cloud/spring-cloud-gateway/issues/3216)

