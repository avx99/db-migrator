package com.avx.migration.config;

import com.avx.migration.jobs.Launcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The main entry point for the MigrationApp application.
 * <p>
 * This class is the entry point for the Spring Boot application. The {@link JobStarterConfig#commandLineRunner(Launcher)} method runs
 * the Spring Boot application jobs, initializing all necessary components and configurations.
 * </p>
 * <p>
 * Additionally, a {@link CommandLineRunner} bean is defined to launch the {@link Launcher#jobLauncher}
 * method when the application starts. This ensures that the migration jobs are executed as soon as the application
 * starts up, provided that the migration functionality is enabled in the application properties.
 * </p>
 */

@Configuration
public class JobStarterConfig {

    /**
     * Provides a {@link CommandLineRunner} bean that is executed on startup.
     * <p>
     * This runner calls the {@link Launcher#jobLauncher} method, which triggers the
     * execution of migration jobs. The context of the application is passed to this method to allow
     * the Launcher to access the necessary Spring beans for migration job execution.
     * </p>
     *
     * @param launcher The {@link Launcher} component that is responsible for launching migration jobs.
     * @return a {@link CommandLineRunner} that runs the migration jobs on startup.
     */
    @Bean
    public CommandLineRunner commandLineRunner(Launcher launcher) {
        return args -> launcher.jobLauncher();
    }
}
