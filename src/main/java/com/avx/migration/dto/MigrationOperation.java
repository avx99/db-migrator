package com.avx.migration.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MigrationOperation(
        LocalDateTime executionDate,
        Job job,
        List<Event> events
) {
}
