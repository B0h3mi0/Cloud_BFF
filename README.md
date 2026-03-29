# Library BFF Microservice

Este proyecto es un microservicio Backend for Frontend (BFF) desarrollado con Java 17 y Spring Boot. Su principal objetivo es orquestar y exponer endpoints REST que se comunican con diferentes funciones serverless (Azure Functions) encargadas de la gestión de Usuarios y Préstamos de libros.

El proyecto está diseñado usando los principios de la **Arquitectura Hexagonal** (Puertos y Adaptadores) y hace uso de **Spring WebFlux (WebClient)** para realizar integraciones proactivas no bloqueantes con los servicios externos de Azure.

## Arquitectura

El proyecto sigue una estructura de capas siguiendo la convención hexagonal:

- **Domain (`com.bff.library.domain`)**: Contiene las entidades y modelos de negocio puros (`User`, `BookLoan`).
- **Application Core (`com.bff.library.application`)**: 
  - `port.in`: Casos de uso e interfaces de entrada que la aplicación expone hacia el exterior (ej. Controladores REST).
  - `port.out`: Interfaces salientes que la aplicación requiere para comunicarse con el mundo externo (Azure).
  - `service`: Implementación concreta de la lógica de negocio en función a los respectivos casos de uso.
- **Infrastructure (`com.bff.library.infrastructure`)**:
  - `in.web`: Controladores API REST (`UserController`, `BookLoanController`, `ExampleController`).
  - `out.azure`: Adaptadores HTTP salientes que implementan los puertos `.out` comunicándose con Azure Functions vía `WebClient` de Reactor.

## Requisitos Previos

- Java 17 o superior.
- Maven 3.8 o superior.
- (Opcional) Contar con las correspondientes Azure Functions en funcionamiento localmente o desplegadas para interactuar con ellas (salvo el endpoint de Ejemplo que ya integra con la nube).

## Configuración (`application.properties`)

Antes de iniciar la aplicación, puedes verificar o ajustar las URLs base de las Azure Functions abriendo `src/main/resources/application.properties`:

```properties
server.port=8080

# URLs base de las funciones en Azure (por defecto apuntando a entorno local)
azure.functions.users.url=http://localhost:7071/api/users
azure.functions.bookloans.url=http://localhost:7071/api/loans

# Función de ejemplo funcional en la nube
azure.functions.example.url=https://cloudnative-duoavengers-gvexfnfvaybbedaz.brazilsouth-01.azurewebsites.net/api/HttpExample
```

## Construcción y Ejecución

1. **Compilar el proyecto (Validar sintaxis y dependencias):**
   ```bash
   mvn clean compile
   ```
2. **Construir el proyecto completo (.jar):**
   ```bash
   mvn clean package
   ```
3. **Ejecutar la aplicación usando Maven:**
   ```bash
   mvn spring-boot:run
   ```
   La aplicación se levantará en el puerto designado (por defecto `http://localhost:8080`).

## Endpoints Expuestos por el BFF

### 1. Azure Function de Ejemplo (Verificación Rápida)
Este endpoint consume y orquesta la Azure Function que se estipuló como de demostración. Es ideal para probar que el BFF y la orquestación HTTP funcionan correctamente sin configurar nada adicional en local.
- **GET** `/api/example`
- **GET** `/api/example?name=TuNombre`

### 2. Usuarios
Endpoints del BFF que a su vez orquestan y delegan las llamadas completas de CRUD hacia la Azure Function de usuarios.
- **GET** `/api/users`
- **GET** `/api/users/{id}`
- **POST** `/api/users` (Enviando JSON formatedo de la entidad)
- **PUT** `/api/users/{id}`
- **DELETE** `/api/users/{id}`

### 3. Préstamos de Libros
Endpoints del BFF para el manejo de préstamos de libros (Book Loans). Se comportan de manera idéntica al modelo de usuarios, orquestando CRUD hacia las funciones objetivo.
- **GET** `/api/loans`
- **GET** `/api/loans/{id}`
- **POST** `/api/loans`
- **PUT** `/api/loans/{id}`
- **DELETE** `/api/loans/{id}`
