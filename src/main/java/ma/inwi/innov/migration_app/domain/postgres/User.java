package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.jobs.spec.Migrable;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(schema = "innov", name = "user")
public class User extends AuditDetails implements Migrable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "keycloak_id")
    private String keycloakId;
    private String version;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String city;
    private String job;
    private String email;
    private String username;
    private String image;
    private String profession;
    @Column(name = "inwi_phone")
    private String inwiPhone;
    private boolean active;
    private String company;
    @Column(name = "education_level")
    private String educationLevel;
    private String skills;
    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @ManyToOne
    private Role role;

//
//    @OneToMany(fetch = FetchType.LAZY)
//    private List<Project> projects;
//
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;

}
