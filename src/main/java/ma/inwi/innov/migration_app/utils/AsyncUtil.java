package ma.inwi.innov.migration_app.utils;



import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * Utility class for running asynchronous tasks using Java 21 virtual threads.
 */

public final class AsyncUtil {

    private AsyncUtil() {
    }

    // Executor that creates a new virtual thread for each task
    private static final ExecutorService VIRTUAL_THREAD_EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

    /**
     * Executes a Runnable task asynchronously using a virtual thread.
     *
     * @param task the Runnable task to execute
     * @return a CompletableFuture representing the asynchronous computation
     */
    public static CompletableFuture<Void> runAsync(Runnable task) {
        return CompletableFuture.runAsync(task, VIRTUAL_THREAD_EXECUTOR);
    }

    /**
     * Executes a Supplier task asynchronously using a virtual thread.
     *
     * @param <T>    the type of the result
     * @param task   the Supplier task to execute
     * @return a CompletableFuture representing the asynchronous computation
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> task) {
        return CompletableFuture.supplyAsync(task, VIRTUAL_THREAD_EXECUTOR);
    }

    /**
     * Submits a Callable task for asynchronous execution using a virtual thread.
     *
     * @param <T>    the type of the result
     * @param task   the Callable task to execute
     * @return a CompletableFuture representing the asynchronous computation
     */
    public static <T> CompletableFuture<T> submit(Callable<T> task) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return task.call();
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        }, VIRTUAL_THREAD_EXECUTOR);
    }

    /**
     * Shuts down the virtual thread executor gracefully.
     * It's recommended to call this method when the application is stopping.
     */
    public static void shutdown() {
        VIRTUAL_THREAD_EXECUTOR.shutdown();
        try {
            if (!VIRTUAL_THREAD_EXECUTOR.awaitTermination(60, TimeUnit.SECONDS)) {
                VIRTUAL_THREAD_EXECUTOR.shutdownNow();
                if (!VIRTUAL_THREAD_EXECUTOR.awaitTermination(60, TimeUnit.SECONDS)) {
                }
            }
        } catch (InterruptedException ie) {
            VIRTUAL_THREAD_EXECUTOR.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
