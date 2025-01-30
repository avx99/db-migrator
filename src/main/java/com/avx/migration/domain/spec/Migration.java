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
public class Migration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String version;

    @OneToMany(mappedBy = "migration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Job> jobs;
}
