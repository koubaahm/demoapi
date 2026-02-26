# Demo API

REST API développée avec Spring Boot 3 et Java 21.

## Stack technique

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Flyway (gestion des migrations)
- H2 (base de données en mémoire)
- Spring Boot Actuator
- JUnit 5 et Mockito
- JaCoCo (rapport de couverture)
- Docker

## Prérequis

### Option 1 — Maven

- Java 21 installé
- Maven installé (ou utiliser le wrapper ./mvnw)

Vérifier la version Java :

```bash
java -version
```

## Lancer l'application avec Maven

Build, tests et génération du rapport JaCoCo :

```bash
./mvnw clean verify
```

Démarrer l’application :

```bash
./mvnw spring-boot:run
```

Application disponible sur :

http://localhost:8080

Health check :

http://localhost:8080/actuator/health

## Lancer avec Docker

Build de l’image :

```bash
docker build -t demoapi .
```

Démarrer le container :

```bash
docker run -p 8080:8080 demoapi
```

Application disponible sur :

http://localhost:8080

## Lancer les tests

```bash
./mvnw test
```

## Rapport de couverture (JaCoCo)

```bash
./mvnw verify
```

Rapport généré dans :

target/site/jacoco/index.html

## API Documentation

Once the application is running, the OpenAPI documentation is available at:

http://localhost:8080/swagger-ui.html

The raw OpenAPI specification can be accessed at:

http://localhost:8080/v3/api-docs

All documented endpoints are prefixed with:

/api/v1

## Base de données

- H2 in-memory
- Migrations versionnées via Flyway
- Hibernate configuré en validate
- Open Session In View désactivé

## Architecture

```
controller
service
repository
domain
dto
mapper
exception
```

Principes appliqués :

- SOLID
- Clean Code
- DTO records
- Mapping explicite
- Gestion centralisée des exceptions
- Tests unitaires avec mocks
- Couverture mesurée avec JaCoCo