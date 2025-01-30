package com.avx.migration.jobs.spec;

/**
 * The {@code Migrable} interface defines a contract for entities that can be migrated
 * in a batch processing context. Entities implementing this interface must provide
 * functionality to set a version, which can be used to track or control their migration status.
 *
 * <p>This interface is intended to be implemented by JPA entities that require
 * versioning during migration processes, allowing the batch processor to manage
 * entity versions effectively.
 *
 * <p>Usage example:
 * <pre>
 * {@code
 * @Entity
 * public class MyEntity implements Migrable {
 *
 *     private String version;
 *
 *     @Override
 *     public void setVersion(String version) {
 *         this.version = version;
 *     }
 *
 *     // Other fields and methods
 * }
 * }
 * </pre>
 *
 * <p>Batch processes can then utilize the {@code setVersion} method to assign
 * or update the version of the entity during migration.
 *
 * @see jakarta.persistence.Entity
 */
public interface Migrable {

    /**
     * Sets the version of the entity, which can be used to track its migration status.
     *
     * @param version the version to be set for the entity
     */
    void setVersion(String version);
}
