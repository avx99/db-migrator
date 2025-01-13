package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.enumeration.ContactSource;
import ma.inwi.innov.migration_app.enumeration.mail.SenderType;
import ma.inwi.innov.migration_app.jobs.spec.Migrable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(schema = "innov", name = "contact")
public class Contact extends AuditDetails implements Migrable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name")
    private String firstName;
    private String version;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "sender_type")
    @Enumerated(EnumType.STRING)
    private SenderType senderType;
    @Column(name = "contact_source")
    @Enumerated(EnumType.STRING)
    private ContactSource contactSource;
    private String mail;
    private String subject;
    private String company;
    private String message;
}
