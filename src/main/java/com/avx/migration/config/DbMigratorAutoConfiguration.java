package com.avx.migration.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Auto-configuration class for the DB Migrator library.
 * <p>
 * This class ensures that all necessary beans under the package {@code com.avx.migration}
 * are automatically detected and registered in the Spring application context.
 * <p>
 * When this library is included as a dependency in a Spring Boot project,
 * all its components (services, repositories, configurations, etc.) are
 * automatically scanned and registered without requiring explicit configuration
 * in the consuming application.
 * <p>
 * Example usage:
 * <pre>
 * &#64;SpringBootApplication
 * public class MyApp {
 *     public static void main(String[] args) {
 *         SpringApplication.run(MyApp.class, args);
 *     }
 * }
 * </pre>
 * The configuration is automatically applied due to Spring Boot's auto-configuration mechanism.
 *
 * @author Your Name
 * @since 1.0.0
 */
@AutoConfiguration // This makes it an auto-configuration class
@ComponentScan(basePackages = "com.avx.migration") // Ensures all beans from db-migrator are loaded
public class DbMigratorAutoConfiguration {
}