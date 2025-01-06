package com.avx.migration.dto;

import lombok.Builder;

@Builder
public record MigrationRequestDto(
        String from,
        String to
) {
}
