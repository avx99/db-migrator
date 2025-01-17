package ma.inwi.innov.migration_app.jobs;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.batch.SequentialExecutor;
import ma.inwi.innov.migration_app.config.MigrationUtilsProperties;
import ma.inwi.innov.migration_app.config.RollbackStrategy;
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
@Slf4j
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
        log.info("Starting job launcher");

        if (RollbackStrategy.NONE.equals(migrationUtilsProperties.getRollbackStrategy())) {
            log.warn("No rollback strategy is applied");
        }

        // Check if migration is enabled via the configuration properties
        if (migrationUtilsProperties.isEnabled()) {
            log.info("Migration feature is enabled, proceeding with job execution");

            // Find executable job classes based on package and versions defined in the configuration
            var jobs = ReflectionUtils.findExecutableClasses("ma.inwi.innov.migration_app.jobs", migrationUtilsProperties.getVersions());

            if (jobs == null || jobs.isEmpty()) {
                log.warn("No jobs found to execute");
                return;
            }

            // Log the number of jobs found
            log.info("Found {} job(s) to execute", jobs.size());

            // Iterate over the jobs and execute each
            for (var job : jobs) {
                try {
                    log.info("Preparing to execute job: {}", job.getLeft().getSimpleName());

                    // Get the Job bean from the application context
                    var jobBean = (Job) ctx.getBean(job.getLeft());

                    // Log the successful retrieval of the job bean
                    log.debug("Retrieved job bean for: {}", job.getLeft().getSimpleName());

                    // Create a SequentialExecutor for the job and execute with the configured batch size
                    var executor = new SequentialExecutor(jobBean, job.getRight());
                    log.info("Executing job with batch size: {}", migrationUtilsProperties.getBatchSize());

                    if (migrationUtilsProperties.getRollbackStrategy() == null || RollbackStrategy.NONE.equals(migrationUtilsProperties.getRollbackStrategy())) {
                        log.info("No rollback strategy is applied, performing migration directly");
                        executor.execute(migrationUtilsProperties.getBatchSize());

                    } else if (RollbackStrategy.FORCE.equals(migrationUtilsProperties.getRollbackStrategy())) {
                        log.info("No migration will be performed only rollback method will be launched");
                        executor.rollback();

                    } else if (RollbackStrategy.ON_ERROR.equals(migrationUtilsProperties.getRollbackStrategy())) {
                        log.info("Performing migration with rollback in case of error while executing");
                        try {
                            executor.execute(migrationUtilsProperties.getBatchSize());
                        } catch (Exception e) {
                            log.error("Getting issue while migrating, performing rollback ...");
                            jobs.forEach(currentJob -> {
                                var currentJobBean = (Job) ctx.getBean(currentJob.getLeft());
                                var currentExecutor = new SequentialExecutor(currentJobBean, currentJob.getRight());
                                currentExecutor.rollback();
                            });
                            log.warn("rollback all jobs");
                            return;
                        }
                    }

                    log.info("Job {} executed successfully", job.getLeft().getSimpleName());

                } catch (Exception e) {
                    log.error("Error executing job {}: {}", job.getLeft().getSimpleName(), e.getMessage(), e);
                }
            }
        } else {
            log.info("Migration feature is disabled, skipping job execution");
        }

        log.info("Job launcher execution completed");
    }
}

