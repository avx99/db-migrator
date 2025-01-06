package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class AuditDetails {

    @Column(name = "last_modified_by", updatable = false)
    private String lastModifiedBy;
    @Column(name = "created_by", updatable = false)
    private String createdBy;
    @Column(name = "deleted_at", updatable = false)
    private LocalDateTime deletedAt;

    @CreationTimestamp

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = false)
    private LocalDateTime updatedAt;

}

