package com.avx.migration.batch.spec;

/**
 * Interface representing an executor that processes tasks in batches.
 * The implementation of this interface is responsible for executing jobs
 * in a batch-wise manner, where the batch size determines the number of
 * records or tasks processed in each iteration.
 *
 * <p>This interface is typically used in scenarios where large datasets
 * need to be processed in smaller, more manageable chunks to optimize
 * performance and resource utilization.
 *
 * <p>Implementations of this interface should provide the logic to execute
 * jobs in batches, ensuring that each batch is handled sequentially or
 * concurrently based on the application's requirements.
 *
 * <p>Usage example:
 * <pre>
 * public class BatchExecutor implements Executor {
 *     &#64;Override
 *     public void execute(Integer batchSize) {
 *         // Logic to process records in batches of the specified size
 *     }
 *
 *     &#64;Override
 *     public void rollback() {
 *         // Logic to process records rollback for a specific version
 *     }
 * }
 * </pre>
 */
public interface Executor {

    /**
     * Executes tasks in batches of the specified size.
     *
     * @param batchSize the number of records or tasks to process in each batch
     */
    void execute(Integer batchSize);

    /**
     * Executes rollback for specified version.
     *
     */
    void rollback();
}
