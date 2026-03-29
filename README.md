Library BFF Microservice
Este proyecto es un microservicio Backend for Frontend (BFF) desarrollado con Java 17 y Spring Boot. Su principal objetivo es orquestar y exponer endpoints REST que se comunican con diferentes funciones serverless (Azure Functions) encargadas de la gestión de Usuarios y Reservas de libros.

El proyecto está diseñado bajo los principios de la Arquitectura Hexagonal (Puertos y Adaptadores) y hace uso de Spring WebFlux (WebClient) para realizar integraciones reactivas y no bloqueantes con los servicios externos de Azure.

Arquitectura
El proyecto sigue una estructura de capas estricta para desacoplar el dominio del framework y las integraciones externas:

Domain (com.bff.library.domain): Contiene las entidades y modelos de negocio puros (User, ReservaLibro).

Application Core (com.bff.library.application):

port.in: Casos de uso e interfaces de entrada que la aplicación expone hacia el exterior.

port.out: Interfaces salientes que la aplicación requiere para comunicarse con el mundo externo.

service: Implementación concreta de la lógica de negocio.

Infrastructure (com.bff.library.infrastructure):

in.web: Controladores API REST (UserController, ReservaController).

out.azure: Adaptadores HTTP salientes que implementan los puertos .out comunicándose con Azure Functions vía WebClient.

Requisitos Previos
Java 17 o superior.

Maven 3.8 o superior.

Docker y Docker Compose (Para despliegue en entornos virtualizados como AWS EC2).

Configuración (application.properties)
Antes de iniciar la aplicación, verifica las URLs base de las Azure Functions en src/main/resources/application.properties:

Properties
server.port=8080

# Azure Functions base URLs
azure.functions.users.url=https://cloudnative-duoavengers-gvexfnfvaybbedaz.brazilsouth-01.azurewebsites.net/api/users
azure.functions.bookloans.url=https://cloudnative-duoavengers-gvexfnfvaybbedaz.brazilsouth-01.azurewebsites.net/api/reservas
Construcción y Ejecución Local
Compilar el proyecto:

Bash
mvn clean compile
Construir el empaquetado completo (.jar):

Bash
mvn clean package
Ejecutar la aplicación:

Bash
mvn spring-boot:run
La aplicación se levantará en http://localhost:8080.

Despliegue con Docker
Para levantar el servicio en un entorno contenerizado junto con su base de datos:

Bash
docker-compose up -d --build
Para revisar los logs de ejecución en tiempo real:

Bash
docker logs -f library-bff-app
Endpoints Expuestos por el BFF
Todas las peticiones a estos endpoints son procesadas por el BFF, el cual orquesta la llamada hacia las funciones serverless de Azure y retorna la respuesta formateada al cliente.

1. Gestión de Usuarios (/api/users)
GET /api/users : Lista todos los usuarios.

GET /api/users/{id} : Obtiene un usuario por su ID.

POST /api/users : Crea un nuevo usuario (Recibe JSON).

PUT /api/users/{id} : Actualiza un usuario existente.

DELETE /api/users/{id} : Elimina un usuario.

2. Gestión de Reservas de Libros (/api/reservas)
GET /api/reservas : Lista todas las reservas.

GET /api/reservas/{id} : Obtiene una reserva por su ID.

POST /api/reservas : Crea una nueva reserva (Requiere un userId válido).

PUT /api/reservas/{id} : Actualiza el estado de una reserva.

DELETE /api/reservas/{id} : Elimina una reserva.
