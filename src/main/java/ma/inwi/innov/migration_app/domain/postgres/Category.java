package ma.inwi.innov.migration_app.domain.postgres;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Category extends AuditDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, length = 191)
    private String name;

    @Column(name = "type_category", nullable = false, length = 191)
    private String typeCategory;

    @Column(name = "active", length = 191)
    private String active;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}