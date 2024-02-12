# Aseguradora - Frontend

Proyecto frontend del sistema **Aseguradora**. Está desarrollado en Angular 16 usando [Ant Design](https://ng.ant.design/docs/introduce/en) como librería estandar para UI.

## Correr proyecto

Primero se deben descargar las depencencias con el comando:

```
npm i
```

Luego para correr el proyecto:

```
ng serve
```

Una vez ejecutado se podrá acceder al frontend en la url [http://localhost:4200/](http://localhost:4200/).

Por defecto usará el ambiente *development*, el cual hará los requests de HTTP a la URL [http://172.17.0.1:9000](http://172.17.0.1:9000). Esta URL es el equivalente a localhost, con el puerto 9000, correspondiente al puerto del gateway. O sea que por defecto apuntará al gateway que esta en local, para luego redirigir los requests a las otras APIs locales.
