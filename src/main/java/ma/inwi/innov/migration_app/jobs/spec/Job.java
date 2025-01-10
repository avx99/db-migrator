package ma.inwi.innov.migration_app.jobs.spec;

/**
 * Interface for defining migration jobs.
 * <p>
 * This interface provides methods to manage the execution of a migration job, including
 * performing the migration task in paginated chunks and retrieving the total size of the data to be migrated.
 * </p>
 */
public interface Job {

    /**
     * Migrates data in a paginated manner.
     * <p>
     * This method is responsible for performing the migration of data, processing it in chunks defined by the
     * `page` and `size` parameters. The method can be implemented to fetch and migrate data in a way that handles
     * large volumes of data efficiently.
     * </p>
     *
     * @param page The current page number for pagination, starting from 0.
     * @param size The number of items to be processed per page.
     */
    void migrate(int page, int size);

    /**
     * Gets the total size of the data to be migrated.
     * <p>
     * This method returns the total number of items that need to be migrated. It can be used to calculate the
     * total number of pages or to show progress during the migration.
     * </p>
     *
     * @return The total number of items to be migrated.
     */
    Long getSize();
}
