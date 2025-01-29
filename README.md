
# db-migrator

## Overview

The db-migrator App is a Spring Boot application designed to facilitate the migration of data from a MySQL database to a PostgreSQL database. The application leverages Spring Batch for batch processing, Feign for service communication, and integrates with Keycloak for authentication.

This app uses a Gradle build system, and it is configurable using the `application.yaml` file, which contains details about the databases, Keycloak integration, and other system parameters.

## Prerequisites

- JDK 17+
- Gradle
- PostgreSQL and MySQL databases
- Keycloak server for authentication

## Configuration

The application configuration is stored in the `application.yaml` file, where key parameters for the databases, Keycloak authentication, and migration utility settings are defined.

### Server Configuration

```yaml
server:
  port: 9000
```

The app runs on port `9000`.

### Datasource Configuration

Two databases are configured: PostgreSQL and MySQL. The credentials are configurable through environment variables.

#### PostgreSQL

```yaml
spring:
  datasource:
    postgres:
      url: jdbc:postgresql://192.168.49.2:30037/migration
      username: ${INNOV_BACKEND_DB_USERNAME:migration}
      password: ${INNOV_BACKEND_DB_PASSWORD:migration}
      driver-class-name: org.postgresql.Driver
      hikari:
        maximum-pool-size: 20
```

#### MySQL

```yaml
spring:
  datasource:
    mysql:
      url: ${OLD_INNOV_BACKEND_DB_URL:jdbc:mysql://192.168.49.2:30036/migration?allowPublicKeyRetrieval=true&useSSL=false&zeroDateTimeBehavior=convertToNull}
      username: ${OLD_INNOV_BACKEND_DB_USERNAME:root}
      password: ${OLD_INNOV_BACKEND_DB_PASSWORD:root}
      driver-class-name: com.mysql.cj.jdbc.Driver
      hikari:
        maximum-pool-size: 20
```

### Keycloak Configuration

The application uses Keycloak for authentication. Both Keycloak configurations (for access to the migration service and the backend) are defined.

```yaml
keycloak:
  realm: innov-external
  auth-server-url: ${IDP_HOST:http://keycloak-innov.xelops.lan/}
  resource: ${CLIENT_ID_KECK:innov-service-api}
  credentials:
    secret: ${CLIENT_KECK_SECRET:IPHJSLYXzEGjFxaZSnNpC8YI4VChSqOm}
```

The credentials for accessing Keycloak, including realm and client details, are set in the YAML.

### Migration Utility Configuration

This section configures the migration utility, such as the location for static files, batch size, and migration versions.

```yaml
migration:
  utils:
    static-files-root: ${MIGRATION_STATIC_FILES_ROOT:/v1/static/data/}
    batch-size: 5
    enabled: true
    versions:
      - "1.0.0"
```

### Feign Clients Configuration

Feign is used for communication with external services. The client URLs are configurable via environment variables.

```yaml
feign:
  clients:
    innov:
      cm: ${MIGRATION_INNOV_CMS_URL:http://localhost:1337}
      service: ${MIGRATION_INNOV_CMS_URL:http://localhost:8090}
```

## Running the Application

To run the application, use the following command:

```bash
./gradlew bootRun
```

This will start the application on port `9000` by default. Ensure the environment variables are set for database connections and Keycloak credentials.

## Architecture

The Migration App follows a microservices-based architecture with several key components:

- **Spring Boot Application**: The core of the application, responsible for handling migration tasks and batch processing.
- **PostgreSQL & MySQL Databases**: The source (MySQL) and destination (PostgreSQL) databases for the migration process.
- **Keycloak**: For user authentication and service communication.
- **Feign Clients**: For external service communication (e.g., CMS).
- **Spring Batch**: For handling batch processing during migration.

## Diagrams

Below is a basic architecture diagram for the Migration App:

1. **Data Flow**: MySQL -> Migration App -> PostgreSQL
2. **User Flow**: Keycloak <-> Migration App


## Conclusion

This application provides a seamless solution for migrating data between different database systems, with secure access and batch processing capabilities. It can be customized further by adjusting the configuration in the `application.yaml`.
