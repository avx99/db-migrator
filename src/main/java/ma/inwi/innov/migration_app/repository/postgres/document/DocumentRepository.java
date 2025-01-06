package ma.inwi.innov.migration_app.repository.postgres.document;

import ma.inwi.innov.migration_app.domain.postgres.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByDocumentId(String documentId);
}
