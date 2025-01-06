package ma.inwi.innov.migration_app.domain.postgres.custom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class ChallengeEvent {

    @Id
    private UUID id;

    @Column
    private String eventId;

    @Column
    private String challengeId;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private LocalDate date;

    @Column
    private String image;

    @Column
    private String status;

    @Column
    private Boolean pinned;

    @Column
    private String type;
}

