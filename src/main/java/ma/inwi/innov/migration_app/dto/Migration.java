package ma.inwi.innov.migration_app.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record Migration(
        String version,
        List<Job> jobs
) {
}
