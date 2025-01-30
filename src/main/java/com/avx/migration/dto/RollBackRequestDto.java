package com.avx.migration.dto;

import lombok.Builder;

@Builder
public record RollBackRequestDto(
        String from,
        String to
) {
}
