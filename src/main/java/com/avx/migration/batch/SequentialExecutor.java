package com.avx.migration.batch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.avx.migration.batch.spec.Executor;
import com.avx.migration.jobs.spec.Job;

/**
 * A single-threaded executor implementation of the {@link Executor} interface.
 * The {@code SequentialExecutor} processes jobs sequentially, one batch at a time,
 * ensuring that each batch is executed in the order determined by the job's requirements.
 *
 * <p>This executor is designed to handle data migrations or batch processing tasks
 * where parallel execution is not required or desired. It divides the total number
 * of records into batches of a specified size and executes each batch sequentially.
 *
 * <p>Usage example:
 * <pre>
 * Job myJob = new MyJobImplementation();
 * Executor executor = new SequentialExecutor(myJob, "v1.10.2");
 * executor.execute(100); // Processes records in batches of 100
 * </pre>
 *
 * <p>Dependencies:
 * <ul>
 *     <li>Uses Lombok annotations {@code @Slf4j} for logging and {@code @AllArgsConstructor} for constructor generation.</li>
 *     <li>Relies on the {@link Job} interface for defining the migration tasks.</li>
 * </ul>
 *
 * <p>Example:
 * <pre>
 * public class MyJobImplementation implements Job {
 *     // Implementation of Job interface methods
 * }
 * </pre>
 */
@Slf4j
@AllArgsConstructor
public class SequentialExecutor implements Executor {

    /**
     * The job to be executed in batches.
     */
    private final Job<?> job;
    private final String version;

    /**
     * Executes the given job in batches of the specified size.
     * The job's data is divided into smaller chunks, with each chunk processed sequentially.
     * Any remaining records that do not fit into a complete batch are processed in a final batch.
     *
     * @param batchSize the number of records to process in each batch
     */
    @Override
    public void execute(Integer batchSize) {
        var count = job.getSize();
        var steps = count / batchSize;
        var left = count % batchSize;

        log.info("Starting execution of job: {}, Total items: {}, Batch size: {}, Total steps: {}, Remaining items: {}", job.getClass().getSimpleName(), count, batchSize, steps, left);

        if (steps == 0 && left != 0) {
            log.info("Executing first batch for items: {}", left);
            job.migrate(0, (int) left, version);
        }

        for (var i = 0; i < steps; i++) {
            log.info("Executing batch {} of {}, Items: {}, Version: {}", i + 1, steps, batchSize, version);
            job.migrate(i, batchSize, version);

            // Log progress
            log.info("Completed batch {} of {}, Remaining items: {}", i + 1, steps, count - ((long) (i + 1) * batchSize));

            // Handle any remaining items after the last full batch
            if (i == steps - 1 && left != 0) {
                log.info("Executing final batch for remaining items: {}", left);
                job.migrate(i + 1, batchSize, version);
            }
        }
        log.info("Execution of job: {} completed. Total items processed: {}", job.getClass().getSimpleName(), count);
    }

    /**
     * Executes the given job rollback method.
     */
    @Override
    public void rollback() {
        log.info("Starting rollback of job: {}", job.getClass().getSimpleName());
        job.rollback(version);
    }

}
