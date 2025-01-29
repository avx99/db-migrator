package ma.inwi.innov.migration_app.dto;

import ma.inwi.innov.migration_app.enumeration.EventType;

import java.time.LocalDateTime;

public record Event(
        EventType eventType,
        String message,
        LocalDateTime dateOfReception
) {
}
