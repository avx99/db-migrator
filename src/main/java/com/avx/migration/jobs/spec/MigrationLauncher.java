package com.avx.migration.jobs.spec;

import com.avx.migration.batch.spec.Executor;

/**
 * Interface for launching migration jobs.
 * <p>
 * The {@code MigrationLauncher} defines the contract for executing migration jobs
 * in a sequential or batch-wise manner, leveraging a {@link Executor}.
 * It retrieves and executes jobs from the specified Spring application context.
 * </p>
 */
public interface MigrationLauncher {
    /**
     * Launches the migration jobs based on the configured properties.
     * <p>
     * This method scans the available jobs in the specified package and executes them using a {@link Executor}.
     * It retrieves the list of jobs to execute, creates an executor for each, and processes the jobs in batches as configured.
     * </p>
     *
     * @see Executor
     * @see Job
     */
    void jobLauncher();
}
