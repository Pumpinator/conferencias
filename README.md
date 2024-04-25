# Sistema de Registro de Conferencias

![alt text](https://i.imgur.com/OWtoyIp.png)

Este proyecto es un sistema de registro de conferencias que permite a los alumnos registrarse para asistir a
conferencias. Los alumnos ingresan sus datos, incluyendo su matrícula, al registrarse para una conferencia. En cada
página de conferencia, se puede encontrar una cuenta regresiva hasta el inicio de la conferencia, una lista de
asistencia y botones para volver o registrarse.

## Objetivo

El objetivo de este sistema es facilitar el registro y la asistencia a conferencias. Una vez registrado para una
conferencia, si se hace una petición GET enviando como parámetro la matrícula del alumno, el sistema registrará la
asistencia del alumno, agregándolo a la tabla de asistentes. Además, utilizando WebSocket, se muestra una alerta en
tiempo real de bienvenida a todos los usuarios.

## Funciones

- Registro de alumnos para conferencias.
- Cuenta regresiva hasta el inicio de la conferencia.
- Lista de asistencia a la conferencia.
- Registro de asistencia en tiempo real con alerta de bienvenida.

## Tecnologías

- Java
- Spring Boot
- Thymeleaf
- MySQL
- WebSocket

## Instalación y Ejecución

Para instalar y ejecutar este proyecto, primero debes clonar el repositorio en tu máquina local. Luego, navega hasta el
directorio del proyecto y ejecuta los siguientes comandos en la terminal:

```bash
mvn clean install
mvn spring-boot:run
```

Alternativamente, puedes ejecutar el proyecto con algunas extensiones de VS Code, o desde un IDE compatible con Maven,
como IntelliJ IDEA. Para hacerlo, simplemente importa el proyecto en el IDE y ejecuta la clase principal.

Por favor, asegúrate de tener todas las dependencias necesarias instaladas en tu sistema antes de intentar ejecutar el
proyecto.