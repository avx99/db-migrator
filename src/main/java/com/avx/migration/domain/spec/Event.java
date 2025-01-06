package com.avx.migration.domain.spec;

import jakarta.persistence.*;
import lombok.*;
import com.avx.migration.enumeration.EventType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EventType eventType;

    private String message;

    @CreationTimestamp
    private LocalDateTime dateOfReception;

    @ManyToOne
    @JoinColumn(name = "migration_operation_id")
    private MigrationOperation migrationOperation;

    @ManyToOne
    @JoinColumn(name = "rollback_operation_id")
    private RollbackOperation rollbackOperation;
}