package ma.inwi.innov.migration_app.dto;

import lombok.Builder;
import ma.inwi.innov.migration_app.enumeration.documents.DocumentType;

@Builder
public record Metadata(DocumentType documentType, String entity) {
}
