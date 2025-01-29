package ma.inwi.innov.migration_app.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ma.inwi.innov.migration_app.jobs.spec.Migrable;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class Event implements Migrable {
    private String version;
}
