package com.avx.migration.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@ConfigurationProperties(prefix = "migration")
public class MigrationUtilsProperties {

    private int batchSize;
    private boolean enabled;
    private RollbackStrategy rollbackStrategy;
    private MigrationMode mode;
    private String jobsPackage;
    private ExecutorType executor;
    private List<String> versions;
}