package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(schema = "innov", name = "user")
public class User extends AuditDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String keycloakId;
    private LocalDate birthDate;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String city;
    private String job;
    private String email;
    private String username;
    private String image;
    private String profession;
    private String inwiPhone;
    private boolean active;
    private String company;
    private String educationLevel;
    private String skills;
    private Integer yearsOfExperience;

    @ManyToOne
    private Role role;


    @OneToMany(fetch = FetchType.LAZY)
    private List<Project> projects;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "events_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> events;

}
