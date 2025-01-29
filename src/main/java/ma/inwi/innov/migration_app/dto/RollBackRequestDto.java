package ma.inwi.innov.migration_app.dto;

import lombok.Builder;

@Builder
public record RollBackRequestDto(
        String from,
        String to
) {
}
