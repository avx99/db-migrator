package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.enumeration.StatusEnum;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(name = "challenge")
public class Challenge extends AuditDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        private String name;
        private LocalDate dateEvent;
        private String pictureUrl;
        private String title;
        private String description;
        private String regulation;
        @Enumerated(EnumType.STRING)
        @Column(name = "status")
        private StatusEnum status;
        @OneToMany(mappedBy = "challenge")
        private List<Project> winners;

        @OneToOne
        private Event event;
}
