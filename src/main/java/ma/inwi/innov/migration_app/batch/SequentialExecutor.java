package ma.inwi.innov.migration_app.batch;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.inwi.innov.migration_app.batch.spec.Executor;
import ma.inwi.innov.migration_app.jobs.spec.Job;

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
 * Executor executor = new SequentialExecutor(myJob);
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
    private final Job job;

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
        for (var i = 0; i < steps; i++) {
            job.migrate(i, batchSize);
            if (i == steps - 1 && left != 0) {
                job.migrate(i + 1, (int) (left));
            }
        }
    }
}
