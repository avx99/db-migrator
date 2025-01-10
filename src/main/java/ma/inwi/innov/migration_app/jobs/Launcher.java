package ma.inwi.innov.migration_app.jobs;

import lombok.RequiredArgsConstructor;
import ma.inwi.innov.migration_app.batch.SequentialExecutor;
import ma.inwi.innov.migration_app.config.MigrationUtilsProperties;
import ma.inwi.innov.migration_app.jobs.spec.Job;
import ma.inwi.innov.migration_app.utils.ReflectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Launcher class responsible for executing migration jobs.
 * <p>
 * The Launcher class scans for and executes jobs in the application context based on configurations in
 * the `MigrationUtilsProperties` class. If the migration feature is enabled, the jobs are executed sequentially,
 * in batches, according to the provided configuration.
 * </p>
 */
@Component
@RequiredArgsConstructor
public class Launcher {

    private final MigrationUtilsProperties migrationUtilsProperties;

    /**
     * Launches the migration jobs based on the configured properties.
     * <p>
     * This method scans the available jobs in the specified package and executes them using a {@link SequentialExecutor}.
     * It retrieves the list of jobs to execute, creates an executor for each, and processes the jobs in batches as configured.
     * </p>
     *
     * @param ctx The Spring {@link ApplicationContext} used to fetch job beans.
     * @see SequentialExecutor
     * @see Job
     */
    public void jobLauncher(ApplicationContext ctx) {
        // Check if migration is enabled via the configuration properties
        if (migrationUtilsProperties.isEnabled()) {
            // Find executable job classes based on package and versions defined in the configuration
            var jobs = ReflectionUtils.findExecutableClasses("ma.inwi.innov.migration_app.jobs", migrationUtilsProperties.getVersions());

            // If jobs are found, iterate over them and execute each
            if (jobs != null) {
                for (var job : jobs) {
                    // Get the Job bean from the application context
                    var jobBean = (Job) ctx.getBean(job);

                    // Create a SequentialExecutor for the job and execute with the configured batch size
                    var executor = new SequentialExecutor(jobBean);
                    executor.execute(migrationUtilsProperties.getBatchSize());
                }
            }
        }
    }
}

