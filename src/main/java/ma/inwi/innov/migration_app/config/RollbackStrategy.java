package ma.inwi.innov.migration_app.config;

/**
 * Enumeration defining rollback strategies for migration jobs.
 * <p>
 * This enum specifies different strategies for handling rollback scenarios during migration:
 * </p>
 * <ul>
 *   <li><strong>NONE</strong>: No rollback is applied. The migration process follows the default behavior without any rollback mechanism.</li>
 *   <li><strong>ON_ERROR</strong>: Rollback is triggered only if an error occurs during the migration execution. The executor catches the error and performs a rollback.</li>
 *   <li><strong>FORCE</strong>: Only the rollback method is executed, and no migration is performed. This is useful for cases where the user explicitly wants to revert changes without executing the migration.</li>
 * </ul>
 */
public enum RollbackStrategy {
    NONE,     // No rollback is applied; the framework retains the old behavior.
    ON_ERROR, // Rollback is applied if an error occurs during migration execution.
    FORCE     // Only the rollback process is executed, bypassing the migration.
}
