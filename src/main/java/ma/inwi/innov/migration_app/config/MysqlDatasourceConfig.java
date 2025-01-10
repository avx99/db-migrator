package ma.inwi.innov.migration_app.config;
import jakarta.persistence.EntityManagerFactory;
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
 * Configuration class for setting up the MySQL data source in the application.
 * This configuration is used for read-only access to a MySQL database.
 *
 * <p>It defines the necessary beans for a MySQL data source, EntityManagerFactory, and transaction management.
 * The configuration ensures that Spring Data JPA repositories are set up to interact with MySQL.
 * The configuration also specifies the entity packages to scan and uses Hibernate as the JPA provider with MySQL-specific dialect.
 *
 * <p>Key features:
 * <ul>
 *     <li>Configures MySQL data source using properties defined in application configuration (with the prefix "spring.datasource.mysql").</li>
 *     <li>Sets up the EntityManagerFactory and configures it with the appropriate MySQL dialect for Hibernate.</li>
 *     <li>Enables transaction management with the JpaTransactionManager for the MySQL data source.</li>
 *     <li>Allows Spring Data JPA repositories for MySQL entities to function correctly.</li>
 * </ul>
 *
 * <p>Usage:
 * The MySQL data source is configured for read-only operations and can be used in repository classes that interact with the `ma.inwi.innov.migration_app.repository.mysql` package.
 *
 * <pre>
 * @Configuration
 * @EnableTransactionManagement
 * @EnableJpaRepositories(
 *     basePackages = "ma.inwi.innov.migration_app.repository.mysql",
 *     entityManagerFactoryRef = "mysqlEntityManagerFactory",
 *     transactionManagerRef = "mysqlTransactionManager"
 * )
 * public class MysqlDatasourceConfig {
 *     ...
 * }
 * </pre>
 *
 * Dependencies:
 * <ul>
 *     <li>{@link DataSourceProperties}: Used to configure the data source properties for MySQL.</li>
 *     <li>{@link LocalContainerEntityManagerFactoryBean}: Configures the JPA entity manager factory for MySQL.</li>
 *     <li>{@link JpaTransactionManager}: Manages transactions for MySQL data source.</li>
 *     <li>{@link HibernateJpaVendorAdapter}: Provides the Hibernate JPA vendor adapter for MySQL.</li>
 * </ul>
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "ma.inwi.innov.migration_app.repository.mysql",
        entityManagerFactoryRef = "mysqlEntityManagerFactory",
        transactionManagerRef = "mysqlTransactionManager"
)
public class MysqlDatasourceConfig {

    /**
     * Bean that provides the configuration properties for the MySQL data source.
     *
     * <p>Uses the prefix "spring.datasource.mysql" from the application properties to bind the data source configuration.
     *
     * @return a {@link DataSourceProperties} object that contains MySQL connection details.
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    /**
     * Bean that creates the MySQL data source for the application.
     *
     * <p>The data source is configured using properties defined in the {@link DataSourceProperties} bean.
     *
     * @return a {@link DataSource} configured for MySQL.
     */
    @Bean
    public DataSource mysqlDataSource() {
        return mysqlDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    /**
     * Bean that sets up the entity manager factory for the MySQL data source.
     *
     * <p>The factory is configured with Hibernate as the JPA provider and set to scan for entities in the
     * "ma.inwi.innov.migration_app.domain.mysql" package. It also uses the MySQL8Dialect for Hibernate.
     *
     * @param dataSource the MySQL data source bean.
     * @return a {@link LocalContainerEntityManagerFactoryBean} for the MySQL data source.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(@Qualifier("mysqlDataSource") DataSource dataSource) {
        var factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        factoryBean.setPackagesToScan("ma.inwi.innov.migration_app.domain.mysql");
        factoryBean.setPersistenceUnitName("mysql");
        var jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        factoryBean.setJpaProperties(jpaProperties);
        return factoryBean;
    }

    /**
     * Bean that sets up the transaction manager for the MySQL data source.
     *
     * <p>This bean uses the {@link JpaTransactionManager} to manage transactions for the MySQL data source.
     *
     * @param entityManagerFactory the MySQL {@link EntityManagerFactory}.
     * @return a {@link PlatformTransactionManager} for MySQL.
     */
    @Bean
    public PlatformTransactionManager mysqlTransactionManager(
            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactory));
    }
}
