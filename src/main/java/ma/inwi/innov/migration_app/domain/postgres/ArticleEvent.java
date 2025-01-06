package ma.inwi.innov.migration_app.domain.postgres;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Table(name = "article_event")
public class ArticleEvent extends AuditDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String articleId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;
}
