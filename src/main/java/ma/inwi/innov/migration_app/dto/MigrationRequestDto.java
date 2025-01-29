package ma.inwi.innov.migration_app.dto;

import lombok.Builder;

@Builder
public record MigrationRequestDto(
        String from,
        String to
) {
}
