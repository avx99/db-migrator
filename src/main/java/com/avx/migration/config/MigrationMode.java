package com.avx.migration.config;

/**
 * Represents the mode in which a migration can be executed.
 * <p>
 * There are two modes available:
 * <ul>
 *     <li>{@link #INTERACTIVE} - The user interacts with a UI to choose parameters before launching the migration.</li>
 *     <li>{@link #NON_INTERACTIVE} - The migration runs automatically without user intervention.</li>
 * </ul>
 */
public enum MigrationMode {
    /**
     * Interactive mode, where the user selects migration parameters via a UI before execution.
     */
    INTERACTIVE,

    /**
     * Non-interactive mode, where the migration is executed directly without user input.
     */
    NON_INTERACTIVE
}
