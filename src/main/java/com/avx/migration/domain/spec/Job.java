package com.avx.migration.domain.spec;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;

    private Boolean active;

    private Boolean executed;

    private Boolean rollBacked;

    private Integer order;

    private String name;

    private String description;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MigrationOperation> migrationOperations;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RollbackOperation> rollbackOperations;

    @ManyToOne
    private Migration migration;
}