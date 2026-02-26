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

---

## Base URL

All endpoints are versioned under:

http://localhost:8080/api/v1

Example:

GET http://localhost:8080/api/v1/products

---## Prérequis

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

## API Documentation

Swagger UI :

http://localhost:8080/swagger-ui.html

OpenAPI specification :

http://localhost:8080/v3/api-docs

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

## Base de données

- H2 in-memory
- Console H2
- http://localhost:8080/h2-console
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