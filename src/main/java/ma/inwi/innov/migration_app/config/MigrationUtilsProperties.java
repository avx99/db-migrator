package ma.inwi.innov.migration_app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "migration.utils")
public class MigrationUtilsProperties {

    private String staticFilesRoot;
    private int batchSize;
    private boolean enabled;
    private RollbackStrategy rollbackStrategy;
    private List<String> versions;
}