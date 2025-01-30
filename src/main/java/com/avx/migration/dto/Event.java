package com.avx.migration.dto;

import com.avx.migration.enumeration.EventType;

import java.time.LocalDateTime;

public record Event(
        EventType eventType,
        String message,
        LocalDateTime dateOfReception
) {
}
