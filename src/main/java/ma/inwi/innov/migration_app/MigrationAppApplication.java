package ma.inwi.innov.migration_app;

import ma.inwi.innov.migration_app.jobs.Launcher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * The main entry point for the MigrationApp application.
 * <p>
 * This class is the entry point for the Spring Boot application. The {@link main(String[])} method runs
 * the Spring Boot application, initializing all necessary components and configurations.
 * </p>
 * <p>
 * Additionally, a {@link CommandLineRunner} bean is defined to launch the {@link Launcher#jobLauncher(ApplicationContext)}
 * method when the application starts. This ensures that the migration jobs are executed as soon as the application
 * starts up, provided that the migration functionality is enabled in the application properties.
 * </p>
 */
@SpringBootApplication
@EnableFeignClients
public class MigrationAppApplication {

    /**
     * The main entry point for the Spring Boot application.
     * <p>
     * This method triggers the initialization and startup of the Spring Boot application by calling
     * {@link SpringApplication#run(Class, String...)} with the {@link MigrationAppApplication} class.
     * </p>
     *
     * @param args Command-line arguments passed to the application at startup.
     */
    public static void main(String[] args) {
        SpringApplication.run(MigrationAppApplication.class, args);
    }

    /**
     * Provides a {@link CommandLineRunner} bean that is executed on startup.
     * <p>
     * This runner calls the {@link Launcher#jobLauncher(ApplicationContext)} method, which triggers the
     * execution of migration jobs. The context of the application is passed to this method to allow
     * the Launcher to access the necessary Spring beans for migration job execution.
     * </p>
     *
     * @param launcher The {@link Launcher} component that is responsible for launching migration jobs.
     * @param ctx      The Spring {@link ApplicationContext} containing the beans needed for migration.
     * @return A {@link CommandLineRunner} that runs the migration jobs on startup.
     */
    @Bean
    public CommandLineRunner commandLineRunner(Launcher launcher, ApplicationContext ctx) {
        return args -> launcher.jobLauncher(ctx);
    }
}
