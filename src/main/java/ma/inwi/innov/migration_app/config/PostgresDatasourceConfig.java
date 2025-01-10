package ma.inwi.innov.migration_app.config;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * Configuration class for setting up the PostgreSQL data source in the application.
 * This configuration is used for write operations on the PostgreSQL database.
 *
 * <p>It defines the necessary beans for a PostgreSQL data source, EntityManagerFactory, and transaction management.
 * The configuration ensures that Spring Data JPA repositories are set up to interact with PostgreSQL for write operations.
 * The configuration also specifies the entity packages to scan and uses Hibernate as the JPA provider with PostgreSQL-specific dialect.
 *
 * <p>Key features:
 * <ul>
 *     <li>Configures PostgreSQL data source using properties defined in application configuration (with the prefix "spring.datasource.postgres").</li>
 *     <li>Sets up the EntityManagerFactory and configures it with the appropriate PostgreSQL dialect for Hibernate.</li>
 *     <li>Enables transaction management with the JpaTransactionManager for the PostgreSQL data source.</li>
 *     <li>Allows Spring Data JPA repositories for PostgreSQL entities to function correctly.</li>
 * </ul>
 *
 * <p>Usage:
 * The PostgreSQL data source is configured for write operations and can be used in repository classes that interact with the `ma.inwi.innov.migration_app.repository.postgres` package.
 *
 * <pre>
 * @Configuration
 * @EnableTransactionManagement
 * @EnableJpaRepositories(
 *     basePackages = "ma.inwi.innov.migration_app.repository.postgres",
 *     entityManagerFactoryRef = "postgresEntityManagerFactory",
 *     transactionManagerRef = "postgresTransactionManager"
 * )
 * public class PostgresDatasourceConfig {
 *     ...
 * }
 * </pre>
 *
 * Dependencies:
 * <ul>
 *     <li>{@link DataSourceProperties}: Used to configure the data source properties for PostgreSQL.</li>
 *     <li>{@link LocalContainerEntityManagerFactoryBean}: Configures the JPA entity manager factory for PostgreSQL.</li>
 *     <li>{@link JpaTransactionManager}: Manages transactions for PostgreSQL data source.</li>
 *     <li>{@link HibernateJpaVendorAdapter}: Provides the Hibernate JPA vendor adapter for PostgreSQL.</li>
 * </ul>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "ma.inwi.innov.migration_app.repository.postgres",
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager"
)
public class PostgresDatasourceConfig {

    /**
     * Bean that provides the configuration properties for the PostgreSQL data source.
     *
     * <p>Uses the prefix "spring.datasource.postgres" from the application properties to bind the data source configuration.
     *
     * @return a {@link DataSourceProperties} object that contains PostgreSQL connection details.
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Bean that creates the PostgreSQL data source for the application.
     *
     * <p>The data source is configured using properties defined in the {@link DataSourceProperties} bean.
     *
     * @return a {@link DataSource} configured for PostgreSQL.
     */
    @Bean
    public DataSource postgresDataSource() {
        return postgresDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    /**
     * Bean that sets up the entity manager factory for the PostgreSQL data source.
     *
     * <p>The factory is configured with Hibernate as the JPA provider and set to scan for entities in the
     * "ma.inwi.innov.migration_app.domain.postgres" package. It also uses the PostgreSQL dialect for Hibernate.
     *
     * @param dataSource the PostgreSQL data source bean.
     * @return a {@link LocalContainerEntityManagerFactoryBean} for the PostgreSQL data source.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(@Qualifier("postgresDataSource") DataSource dataSource) {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        factoryBean.setPackagesToScan("ma.inwi.innov.migration_app.domain.postgres");
        factoryBean.setPersistenceUnitName("postgres");
        var jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        factoryBean.setJpaProperties(jpaProperties);

        return factoryBean;
    }

    /**
     * Bean that sets up the transaction manager for the PostgreSQL data source.
     *
     * <p>This bean uses the {@link JpaTransactionManager} to manage transactions for the PostgreSQL data source.
     *
     * @param entityManagerFactory the PostgreSQL {@link LocalContainerEntityManagerFactoryBean}.
     * @return a {@link PlatformTransactionManager} for PostgreSQL.
     */
    @Bean
    public PlatformTransactionManager postgresTransactionManager(
            @Qualifier("postgresEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory.getObject()));
    }
}
