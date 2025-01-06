package com.avx.migration.utils.factory;

import com.avx.migration.batch.SequentialExecutor;
import com.avx.migration.batch.spec.Executor;
import com.avx.migration.config.ExecutorType;
import com.avx.migration.jobs.spec.Job;

/**
 * Factory class for creating instances of {@link Executor} based on the provided {@link ExecutorType}.
 */
public class ExecutorFactory {

    private ExecutorFactory() {
    }

    /**
     * Returns an instance of {@link Executor} based on the given {@link ExecutorType}.
     *
     * @param type    the type of executor required
     * @param job     job clazz
     * @param version job version
     * @return an instance of {@link Executor}
     * @throws IllegalArgumentException if the executor type is not supported
     */
    public static Executor getExecutor(ExecutorType type, Job<?> job, String version) {
        return switch (type) {
            case SEQUENTIAL -> new SequentialExecutor(job, version);
            default -> throw new IllegalArgumentException("Unsupported ExecutorType: " + type);
        };
    }
}