package ma.inwi.innov.migration_app.domain.postgres.document;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.domain.postgres.AuditDetails;
import ma.inwi.innov.migration_app.enumeration.documents.DocumentType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(schema = "innov", name = "innov_document")
public class Document extends AuditDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "document_id")
    private String documentId;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    @Column(name = "entity_name")
    private String entityName;

    private String location;

    private String filename;

    private Long size;

    @Column(name = "mime_type")
    private String mimeType;
}
