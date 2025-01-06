package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.enumeration.StatusEnum;

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
@Table(name = "event")
public class Event extends AuditDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
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
    private String pictureUrl;
    private String location;
    //TODO : add them
    @Transient
    private String city;
    private Boolean participation;
    private boolean pinned;
    private String eventTitle;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;
    private String description;
    private boolean publish;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<EventMedia> eventMedias;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<ArticleEvent> eventArticles;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Planning> plannings;

    @OneToMany(mappedBy = "event", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<EventWinner> winners;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Speaker> speakers;

    //TODO : add them
    @Transient
    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Category> categories;

    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Partner> partners;
}
