package ma.inwi.innov.migration_app.jobs.spec;

import ma.inwi.innov.migration_app.dto.Migration;

import java.util.List;

/**
 * Service interface for managing migrations.
 */
public interface MigrationService {

    /**
     * Retrieves a list of all available migrations.
     *
     * @return a list of {@link Migration} objects representing the migrations.
     */
    List<Migration> getMigrations();

    /**
     * Performs a migration from one version to another.
     *
     * @param from the version to migrate from.
     * @param to   the version to migrate to.
     */
    void migrate(String from, String to);

    /**
     * Rolls back a migration from one version to another.
     *
     * @param from the version to roll back from.
     * @param to   the version to roll back to.
     */
    void rollback(String from, String to);
}