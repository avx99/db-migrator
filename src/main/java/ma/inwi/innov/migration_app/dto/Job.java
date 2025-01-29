package ma.inwi.innov.migration_app.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record Job(
        String version,
        Boolean active,
        Boolean executed,
        Boolean rollBacked,
        Integer order,
        String name,
        String description,
        List<MigrationOperation> migrationOperations,
        List<RollbackOperation> rollbackOperations
) {
}
