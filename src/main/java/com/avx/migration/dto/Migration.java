package com.avx.migration.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record Migration(
        String version,
        List<Job> jobs
) {
}
