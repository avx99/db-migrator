
# db-migrator

## Overview

The db-migrator App is a Spring Boot application designed to facilitate the migration of data from sql databases.

This app uses a Gradle build system, and it is configurable using the `application.yaml` file, which contains details about the databases and other system parameters.

## Prerequisites

- JDK 21
- Gradle
- SQL database to store history of operations

## Configuration

The application configuration is stored in the `application.yaml` file, where key parameters for the databases, and migration utility settings are defined.


### Datasource Configuration

One database is configured

#### PostgreSQL (or any sql db)

```yaml
migration:
  datasource:
    url: jdbc:postgresql://172.27.210.242:30037/migration
    username: migration
    password: migration
    driver-class-name: org.postgresql.Driver
```


### Migration Utility Configuration

This section configures the migration utility, such as the location for static files, batch size, and migration versions.

```yaml
migration:
  utils:
    batch-size: 5
    enabled: true
    versions:
      - "1.0.0"
```

```
## Conclusion

This application provides a seamless solution for migrating data between different database systems, with secure access and batch processing capabilities. It can be customized further by adjusting the configuration in the `application.yaml`.
