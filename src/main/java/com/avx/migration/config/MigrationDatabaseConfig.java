package com.avx.migration.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class MigrationDatabaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "migration.datasource")
    public DataSource migrationDataSource() {
        return new DriverManagerDataSource();
    }
}