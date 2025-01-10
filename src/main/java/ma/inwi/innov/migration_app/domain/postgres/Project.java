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
@Table(schema = "innov", name = "project")
public class Project extends AuditDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String startupName;
    private String city;
    private String country;
    private String address;
    private Integer numberOfEmployees;
    private String linkedinUrl;
    private String websiteUrl;
    private LocalDate date;
    private String businessField;
    private String problem;
    private String description;
    private String fileUrl;
    private String videoUrl;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;
    private String regulation;
    private String maturityStage;
    private Boolean pinned;
    private String title;
    private String imageUrl;

    @ManyToOne
    private User jury;

    @ManyToOne
    private User depositor;

    @ManyToOne
    private Challenge challenge;

    @OneToMany(mappedBy = "project")
    private List<Evaluation> evaluations;

}
