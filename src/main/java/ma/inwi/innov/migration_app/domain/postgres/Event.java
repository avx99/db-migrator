package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.enumeration.StatusEnum;
import ma.inwi.innov.migration_app.jobs.spec.Migrable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(schema = "innov", name = "event")
public class Event extends AuditDetails implements Migrable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    //TODO : add them
    private String version;
    private LocalDate date;
    //TODO : add them
    @Transient
    private LocalDateTime startInscriptionDate;
    //TODO : add them
    @Transient
    private LocalDateTime endInscriptionDate;
    //TODO : add them
    @Transient
    private Boolean migrated;
    //TODO : add them
    @Transient
    private Boolean isLive;
    private LocalTime time;
    @Column(name = "picture_url")
    private String pictureUrl;
    private String location;
    //TODO : add them
    @Transient
    private String city;
    private Boolean participation;
    private boolean pinned;
    @Column(name = "event_title")
    private String eventTitle;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;
    private String description;
    private boolean publish;

    @Column(name = "event_medias")
    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<EventMedia> eventMedias;

    @Column(name = "event_articles")
    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ArticleEvent> eventArticles;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Planning> plannings;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<EventWinner> winners;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<EventUser> eventUsers;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Speaker> speakers;

    //TODO : add them
    @Transient
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Category> categories;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Partner> partners;
}
