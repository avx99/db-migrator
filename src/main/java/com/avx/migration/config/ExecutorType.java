package com.avx.migration.config;

/**
 * Enum representing the different types of executors used for migration processing.
 * Currently, only the {@link #SEQUENTIAL} executor type is available, which processes
 * migrations in a batch-by-batch manner using a single thread.
 */
public enum ExecutorType {

    /**
     * SEQUENTIAL executor type.
     * This executor processes migrations in sequence, handling one batch at a time using a single thread.
     */
    SEQUENTIAL
}