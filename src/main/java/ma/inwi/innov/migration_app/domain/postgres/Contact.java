package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.enumeration.ContactSource;
import ma.inwi.innov.migration_app.enumeration.mail.SenderType;


@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(name = "contact")
public class Contact extends AuditDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private SenderType senderType;
    @Enumerated(EnumType.STRING)
    private ContactSource contactSource;
    private String mail;
    private String subject;
    private String company;
    private String message;
}
