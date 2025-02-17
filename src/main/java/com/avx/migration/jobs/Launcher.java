package com.avx.migration.jobs;

import com.avx.migration.annotations.Executable;
import com.avx.migration.config.MigrationMode;
import com.avx.migration.config.MigrationUtilsProperties;
import com.avx.migration.config.RollbackStrategy;
import com.avx.migration.dto.Migration;
import com.avx.migration.jobs.spec.Job;
import com.avx.migration.jobs.spec.MigrationLauncher;
import com.avx.migration.jobs.spec.MigrationService;
import com.avx.migration.utils.ReflectionUtils;
import com.avx.migration.utils.VersionsUtils;
import com.avx.migration.utils.factory.ExecutorFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
public class Launcher implements MigrationService, MigrationLauncher {

    private final MigrationUtilsProperties migrationUtilsProperties;
    private final ApplicationContext ctx;

    @Override
    public void jobLauncher() {
        log.info("Starting job launcher");

        // Check rollback strategy
        if (RollbackStrategy.NONE.equals(migrationUtilsProperties.getRollbackStrategy())) {
            log.warn("No rollback strategy is applied");
        }

        if (!MigrationMode.NON_INTERACTIVE.equals(migrationUtilsProperties.getMode())) {
            log.info("Non interactive mode is chosen, please access the UI in order to launch jobs manually");
            return;
        }

        // Check if migration is enabled via the configuration properties
        if (migrationUtilsProperties.isEnabled()) {
            log.info("Migration feature is enabled, proceeding with job execution");

            // Find executable job classes based on package and versions defined in the configuration
            var jobs = ReflectionUtils.findExecutableClasses(migrationUtilsProperties.getJobsPackage(), migrationUtilsProperties.getVersions(), Boolean.TRUE);

            if (jobs == null || jobs.isEmpty()) {
                log.warn("No jobs found to execute");
                return;
            }

            // Log the number of jobs found
            log.info("Found {} job(s) to execute", jobs.size());
            processJobs(jobs, ctx);
        } else {
            log.info("Migration feature is disabled, skipping job execution");
        }

        log.info("Job launcher execution completed");
    }

    private void processJobs(List<Pair<Class<?>, String>> jobs, ApplicationContext ctx) {
        // Iterate over the jobs and execute each
        for (var job : jobs) {
            try {
                log.info("Preparing to execute job: {}", job.getLeft().getSimpleName());

                // Get the Job bean from the application context
                var jobBean = (Job) ctx.getBean(job.getLeft());

                // Log the successful retrieval of the job bean
                log.debug("Retrieved job bean for: {}", job.getLeft().getSimpleName());

                // Create a SequentialExecutor for the job and execute with the configured batch size
                var executor = ExecutorFactory.getExecutor(migrationUtilsProperties.getExecutor(), jobBean, job.getRight());
                log.info("Executing job with batch size: {}", migrationUtilsProperties.getBatchSize());

                if (migrationUtilsProperties.getRollbackStrategy() == null || RollbackStrategy.NONE.equals(migrationUtilsProperties.getRollbackStrategy())) {
                    log.info("No rollback strategy is applied, performing migration directly");
                    executor.execute(migrationUtilsProperties.getBatchSize());

                } else if (RollbackStrategy.FORCE.equals(migrationUtilsProperties.getRollbackStrategy())) {
                    log.info("No migration will be performed only rollback method will be launched");
                    rollback(jobs, ctx);
                    return;

                } else if (RollbackStrategy.ON_ERROR.equals(migrationUtilsProperties.getRollbackStrategy())) {
                    log.info("Performing migration with rollback in case of error while executing");
                    try {
                        executor.execute(migrationUtilsProperties.getBatchSize());
                    } catch (Exception e) {
                        log.error("Getting issue while migrating, performing rollback ...");
                        rollback(jobs, ctx);
                        return;
                    }
                }

                log.info("Job {} executed successfully", job.getLeft().getSimpleName());

            } catch (Exception e) {
                log.error("Error executing job {}: {}", job.getLeft().getSimpleName(), e.getMessage(), e);
            }
        }
    }


    @Override
    public List<Migration> getMigrations() {
        var jobList = ReflectionUtils.findExecutableClasses(migrationUtilsProperties.getJobsPackage(), migrationUtilsProperties.getVersions(), Boolean.FALSE);
        var jobs = new HashMap<String, List<com.avx.migration.dto.Job>>();
        var migrations = new ArrayList<Migration>();
        jobList.forEach(job -> {
            if (job.getLeft().isAnnotationPresent(Executable.class)) {
                var executable = job.getLeft().getAnnotation(Executable.class);

                var jobVersion = executable.version();
                var jobEnabled = executable.enable();
                var jobOrder = executable.order();
                var jobDescription = executable.description();
                var jobName = job.getLeft().getAnnotation(Component.class).value();

                var jobDto = com.avx.migration.dto.Job
                        .builder()
                        .version(jobVersion)
                        .name(jobName)
                        .description(jobDescription)
                        .active(jobEnabled)
                        .order(Integer.parseInt(jobOrder))
                        .build();

                jobs.compute(jobVersion, (k, v) -> {
                    if (v == null) {
                        v = new ArrayList<>();
                    }
                    v.add(jobDto);
                    return v;
                });
            }
        });
        jobs.forEach((k, v) -> migrations.add(Migration.builder().version(k).jobs(v).build()));
        return migrations;
    }

    @Override
    public void migrate(String from, String to) {
        var jobs = getRangeOfJobs(from, to);
        processJobs(jobs, ctx);
    }


    @Override
    public void rollback(String from, String to) {
        // the order switched since it's a rollback
        var jobs = getRangeOfJobs(to, from);
        rollback(jobs, ctx);
    }

    private ArrayList<Pair<Class<?>, String>> getRangeOfJobs(String from, String to) {
        var jobList = ReflectionUtils.findExecutableClasses(migrationUtilsProperties.getJobsPackage(), migrationUtilsProperties.getVersions(), Boolean.TRUE);
        var jobs = new ArrayList<Pair<Class<?>, String>>();
        assert jobList != null;
        jobList.forEach(job -> {
            if (VersionsUtils.isVersionInRange(job.getRight(), from, to)) {
                jobs.add(Pair.of(job.getLeft(), job.getRight()));
            }
        });
        return jobs;
    }

    private void rollback(List<Pair<Class<?>, String>> jobs, ApplicationContext ctx) {
        jobs.sort(Comparator.comparing((Pair<Class<?>, ?> clazz) -> {
                            var annotation = clazz.getLeft().getAnnotation(Executable.class);
                            return annotation.version();  // Compare by version
                        }, Comparator.reverseOrder())  // Reverse the order for version to make it descending
                        .thenComparingInt(clazz -> {
                            var annotation = clazz.getLeft().getAnnotation(Executable.class);
                            return Integer.parseInt(annotation.order());  // Compare order in ascending
                        })
        );

        jobs.forEach(currentJob -> {
            var currentJobBean = (Job) ctx.getBean(currentJob.getLeft());
            var currentExecutor = ExecutorFactory.getExecutor(migrationUtilsProperties.getExecutor(), currentJobBean, currentJob.getRight());
            currentExecutor.rollback();
        });
        log.warn("rollback all jobs");
    }

}

