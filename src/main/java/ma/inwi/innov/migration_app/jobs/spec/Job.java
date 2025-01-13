package ma.inwi.innov.migration_app.jobs.spec;

/**
 * Interface for defining migration jobs for entities that implement the {@link Migrable} interface.
 * <p>
 * This interface provides methods to manage the execution of a migration job, including
 * performing the migration task in paginated chunks and retrieving the total size of the data to be migrated.
 * </p>
 * <p>
 * Entities processed by implementations of this interface must implement the {@link Migrable} interface,
 * which allows them to be versioned and managed during the migration process.
 * </p>
 *
 * @param <T> The type of entity to be migrated, which must extend {@link Migrable}.
 */
public interface Job<T extends Migrable> {

    /**
     * Migrates data in a paginated manner.
     * <p>
     * This method is responsible for performing the migration of data, processing it in chunks defined by the
     * {@code page} and {@code size} parameters. It allows for efficient handling of large volumes of data
     * by dividing the task into manageable pages.
     * </p>
     *
     * @param page The current page number for pagination, starting from 0.
     * @param size The number of items to be processed per page.
     * @param version the version of the migration (used to tag migrated records in db)
     */
    void migrate(int page, int size, String version);

    /**
     * Gets the total size of the data to be migrated.
     * <p>
     * This method returns the total number of items that need to be migrated. It can be used to calculate the
     * total number of pages or to show progress during the migration process.
     * </p>
     *
     * @return The total number of items to be migrated.
     */
    Long getSize();
}
