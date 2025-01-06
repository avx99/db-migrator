package com.avx.migration.api.rest;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import com.avx.migration.dto.Migration;
import com.avx.migration.dto.MigrationRequestDto;
import com.avx.migration.dto.RollBackRequestDto;
import com.avx.migration.jobs.spec.MigrationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing migrations only in case of interactive mode.
 */
@RestController
@RequestMapping("/api/migrations")
@Tag(name = "Migration API", description = "Endpoints for managing migrations")
@RequiredArgsConstructor
@ConditionalOnProperty(name = "migration.utils.mode", havingValue = "INTERACTIVE")
public class MigrationController {

    private final MigrationService migrationService;


    /**
     * Retrieves a list of all available migrations.
     *
     * @return a list of {@link Migration} objects representing the migrations.
     */
    @GetMapping
    @Operation(summary = "Get all migrations", description = "Retrieves a list of all available migrations.")
    public ResponseEntity<List<Migration>> getMigrations() {
        List<Migration> migrations = migrationService.getMigrations();
        return ResponseEntity.ok(migrations);
    }

    /**
     * Performs a migration from one version to another.
     *
     * @param request the migration request body containing the 'from' and 'to' versions.
     */
    @PostMapping("/migrate")
    @Operation(summary = "Perform migration", description = "Migrates from one version to another.")
    public ResponseEntity<Void> migrate(@RequestBody MigrationRequestDto request) {
        migrationService.migrate(request.from(), request.to());
        return ResponseEntity.ok().build();
    }

    /**
     * Rolls back a migration from one version to another.
     *
     * @param request the rollback request body containing the 'from' and 'to' versions.
     */
    @PostMapping("/rollback")
    @Operation(summary = "Rollback migration", description = "Rolls back a migration from one version to another.")
    public ResponseEntity<Void> rollback(@RequestBody RollBackRequestDto request) {
        migrationService.rollback(request.from(), request.to());
        return ResponseEntity.ok().build();
    }
}